package com.library.domain.customers;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.Validate;

import com.core.ddd.AggregateRoot;
import com.library.domain.books.Book;

public class User extends AggregateRoot<Integer> {
	private UserName _name;
	private UserEmail _email;
	private List<RentedBook> _rentedBooks;

	public User(final UserName name, final UserEmail email) {
		_name = Validate.notNull(name, "name must not be null");
		_email = Validate.notNull(email, "email must not be null");
	}

	public UserName getName() {
		return _name;
	}

	public UserEmail getEmail() {
		return _email;
	}

	public Collection<RentedBook> getRentedBooks() {
		return Collections.unmodifiableCollection(_rentedBooks);
	}

	public boolean hasRentedBook(final Book book) {
		Validate.notNull(book, "book must not be null");
		return !_rentedBooks.stream().filter(x -> x.getBook().equals(book)).collect(Collectors.toList()).isEmpty();
	}

	public void rentBook(final Book book) {
		Validate.notNull(book, "book must not be null");
		Validate.validState(!hasRentedBook(book), "book is already rented");

		_rentedBooks.add(new RentedBook(book, this));
	}
}
