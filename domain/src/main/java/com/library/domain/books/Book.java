package com.library.domain.books;

import com.core.ddd.Entity;

public class Book extends Entity<Integer> {
	private String _name;

	public Book(final String name) {
		_name = name;
	}

	public String getName() {
		return _name;
	}
}
