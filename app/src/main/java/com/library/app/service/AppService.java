package com.library.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.core.utils.Result;
import com.library.app.persistence.BookRepository;
import com.library.app.persistence.CustomerRepository;
import com.library.domain.books.Book;
import com.library.domain.users.RentedBook;
import com.library.domain.users.User;
import com.library.domain.users.UserEmail;
import com.library.domain.users.UserName;

public class AppService {
	private CustomerRepository _userRepo;
	BookRepository _bookRepo;

	public AppService(CustomerRepository userRepo, BookRepository bookRepo) {
		_userRepo = userRepo;
		_bookRepo = bookRepo;
	}

	public Result<UserWithBooksDto> getUser(Long userID) {
		User user = _userRepo.getUser(userID);
		if (user == null) {
			return Result.error("user not found");
		}

		return Result.ok(makeUserWithBooksDto(user));
	}

	public List<UserDto> getUserList() {
		return _userRepo.getUserList().stream()
				.map(x -> new UserDto(x.getID(), x.getName().getValue(), x.getEmail().getValue()))
				.collect(Collectors.toList());
	}

	public Result<UserDto> createUser(CreateUserDto createDto) {
		Result<UserName> userName = UserName.from(createDto.getName());
		if (userName.isError()) {
			return Result.error(userName.getError());
		}
		Result<UserEmail> userEmail = UserEmail.from(createDto.getEmail());
		if (userEmail.isError()) {
			return Result.error(userEmail.getError());
		}
		User newUser = new User(userName.getValue(), userEmail.getValue());

		_userRepo.save(newUser);
		return Result.ok(new UserDto(newUser.getID(), newUser.getName().getValue(), newUser.getEmail().getValue()));
	}

	public Result<UserDto> updateUser(Long userID, UpdateUserDto updateDto) {
		User user = _userRepo.getUser(userID);
		if (user == null) {
			return Result.error("user not found");
		}
		Result<UserEmail> userEmail = UserEmail.from(updateDto.getEmail());
		if (userEmail.isError()) {
			return Result.error(userEmail.getError());
		}

		user.setEmail(userEmail.getValue());
		_userRepo.save(user);
		return Result.ok(new UserDto(user.getID(), user.getName().getValue(), user.getEmail().getValue()));
	}

	public void deleteUser(Long userID) {
		_userRepo.deleteUser(userID);
	}

	public Result<UserWithBooksDto> rentBook(Long userID, Long bookID) {
		User user = _userRepo.getUser(userID);
		if (user == null) {
			return Result.error("user not found");
		}
		Book book = _bookRepo.getBook(bookID);
		if (book == null) {
			return Result.error("book not found");
		}

		if (user.hasRentedBook(book)) {

		}
		user.rentBook(book);
		_userRepo.save(user);
		return Result.ok(makeUserWithBooksDto(user));
	}

	private static UserWithBooksDto makeUserWithBooksDto(User user) {
		List<RentedBookDto> rentedBooks = new ArrayList<>();
		for (RentedBook rentedBook : user.getRentedBooks()) {
			BookDto bookDto = new BookDto(rentedBook.getBook().getID(), rentedBook.getBook().getName());
			RentedBookDto rentedBookDto = new RentedBookDto(rentedBook.getExpiryDate(), bookDto);
			rentedBooks.add(rentedBookDto);
		}
		return new UserWithBooksDto(user.getID(), user.getName().getValue(), user.getEmail().getValue(), rentedBooks);
	}
}
