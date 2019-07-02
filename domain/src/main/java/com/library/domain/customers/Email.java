package com.library.domain.customers;

import java.util.regex.Pattern;

import com.core.ddd.ValueObject;
import com.core.utils.Result;

public class Email extends ValueObject {

	private static final long serialVersionUID = 1L;

	private String _value;
	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern
			.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	private Email(String value) {
		_value = value;
	}

	public String getValue() {
		return _value;
	}

	public static Result<Email> Create(String value) {
		value = value.trim();

		if (value.isEmpty())
			return Result.error("Email should not be empty");

		if (!VALID_EMAIL_ADDRESS_REGEX.matcher(value).find())
			return Result.error("Email format is invalid");

		return Result.ok(new Email(value));
	}
}
