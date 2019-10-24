package com.library.domain.users;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.Validate;

import com.core.ddd.AggregateRoot;
import com.library.domain.books.Book;

public class User extends AggregateRoot<Long> {
	private String _name;
	private String _email;
	private List<RentedBook> _rentedBooks;

	public User(final UserName name, final UserEmail email) {
		_name = Validate.notNull(name, "name must not be null").getValue();
		_email = Validate.notNull(email, "email must not be null").getValue();
	}

	public UserName getName() {
		return UserName.from(_name).getValue();
	}

	public UserEmail getEmail() {
		return UserEmail.from(_email).getValue();
	}

	public void setEmail(UserEmail email) {
		_email = Validate.notNull(email, "email must not be null").getValue();
	}

	public Collection<RentedBook> getRentedBooks() {
		return Collections.unmodifiableCollection(_rentedBooks);
	}

	public boolean hasRentedBook(final Book book) {
		Validate.notNull(book, "book must not be null");
		return _rentedBooks.stream().filter(x -> x.getBook().equals(book) && !x.getExpiryDate().isExpired()).findFirst()
				.isPresent();
	}

	public void rentBook(final Book book) {
		Validate.notNull(book, "book must not be null");
		Validate.validState(!hasRentedBook(book), "book is already rented");
		_rentedBooks.add(new RentedBook(book, this, ExpiryDate.DEFAULT));
	}
}
