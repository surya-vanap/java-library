package com.library.app.service;

import java.util.List;

public class UserWithBooksDto {
	private Long _id;
	private String _name;
	private String _email;
	private List<RentedBookDto> _rentedBooks;

	public UserWithBooksDto(Long id, String name, String email, List<RentedBookDto> rentedBooks) {
		_name = name;
		_email = email;
		_rentedBooks = rentedBooks;
		_id = id;
	}

}
