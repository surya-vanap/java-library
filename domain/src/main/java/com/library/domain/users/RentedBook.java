package com.library.domain.users;

import java.time.LocalDate;

import org.apache.commons.lang3.Validate;

import com.core.ddd.Entity;
import com.library.domain.books.Book;

public class RentedBook extends Entity<Integer> {

	private LocalDate _date;
	private Book _book;
	private User _user;

	public RentedBook(final Book book, final User user, final ExpiryDate date) {
		_book = Validate.notNull(book, "book must not be null");
		_user = Validate.notNull(user, "user must not be null");
		_date = Validate.notNull(date, "date must not be null").getValue();
	}

	public Book getBook() {
		return _book;
	}

	public User getUser() {
		return _user;
	}

	public ExpiryDate getExpiryDate() {
		return ExpiryDate.from(_date);
	}
}
