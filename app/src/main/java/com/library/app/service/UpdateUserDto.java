package com.library.app.service;

public class UpdateUserDto {
	String _email;

	public UpdateUserDto(String email) {
		_email = email;
	}

	public String getEmail() {
		return _email;
	}
}
