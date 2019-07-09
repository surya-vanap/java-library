package com.library.domain.customers;

import org.apache.commons.lang3.Validate;

import com.core.ddd.Entity;
import com.library.domain.books.Book;

public class RentedBook extends Entity<Integer> {

	private Book _book;
	private User _user;

	public RentedBook(final Book book, final User user) {
		_book = Validate.notNull(book, "book must not be null");
		_user = Validate.notNull(user, "user must not be null");
	}

	public Book getBook() {
		return _book;
	}

	public User getUser() {
		return _user;
	}
}
