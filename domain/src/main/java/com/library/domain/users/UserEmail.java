package com.library.domain.users;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.core.ddd.ValueObject;
import com.core.utils.Result;

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

	public static Result<UserEmail> from(final String email) {
		if (StringUtils.isBlank(email)) {
			return Result.error("email should not be null or empty");
		}
		if (!isEmailValid(email)) {
			return Result.error("email format is invalid");
		}
		return Result.ok(new UserEmail(email));
	}
}
