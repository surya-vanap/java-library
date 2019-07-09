package com.library.domain.customers;

import org.apache.commons.lang3.Validate;

import com.core.ddd.ValueObject;

public class UserName extends ValueObject {

	private static final long serialVersionUID = 1L;
	private String _value;
	private static final int MAX_LENGTH = 100;

	private UserName(String value) {
		_value = value;
	}

	public String getValue() {
		return _value;
	}

	public static UserName Create(final String name) {
		Validate.notBlank(name, "name should not be empty");
		Validate.isTrue(name.length() <= MAX_LENGTH, "name cannot be more than " + MAX_LENGTH + " chars");
		return new UserName(name);
	}
}
