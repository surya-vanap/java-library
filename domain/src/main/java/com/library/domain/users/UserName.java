package com.library.domain.users;

import org.apache.commons.lang3.StringUtils;

import com.core.ddd.ValueObject;
import com.core.utils.Result;

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

	public static Result<UserName> from(final String name) {
		if (StringUtils.isBlank(name)) {
			return Result.error("name should not be null or empty");
		}
		if (name.length() > MAX_LENGTH) {
			return Result.error("name cannot be more than " + MAX_LENGTH + " chars");
		}
		return Result.ok(new UserName(name));
	}
}
