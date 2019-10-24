package com.library.app.service;

public class CreateUserDto {
	private String _name;
	private String _email;

	public CreateUserDto(String name, String email) {
		_name = name;
		_email = email;
	}

	public String getName() {
		return _name;
	}

	public String getEmail() {
		return _email;
	}
}
