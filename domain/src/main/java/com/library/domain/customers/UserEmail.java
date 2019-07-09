package com.library.domain.customers;

import java.util.regex.Pattern;

import org.apache.commons.lang3.Validate;

import com.core.ddd.ValueObject;

public class UserEmail extends ValueObject {

	private static final long serialVersionUID = 1L;
	private String _value;
	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern
			.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	private UserEmail(String value) {
		_value = value;
	}

	public String getValue() {
		return _value;
	}

	public static boolean isEmailValid(final String email) {
		return VALID_EMAIL_ADDRESS_REGEX.matcher(email).find();
	}

	public static UserEmail Create(final String email) {
		Validate.notBlank(email, "email should not be empty");
		Validate.isTrue(isEmailValid(email), "email format is invalid");
		return new UserEmail(email);
	}
}
