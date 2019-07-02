package com.library.domain.customers;

import java.time.LocalDateTime;

import com.core.ddd.ValueObject;
import com.core.utils.Result;

public class ExpirationDate extends ValueObject {

	private static final long serialVersionUID = 1L;

	public static final ExpirationDate INFINITE = new ExpirationDate(null);
	private LocalDateTime _value;

	public LocalDateTime getValue() {
		return _value;
	}

	private ExpirationDate(LocalDateTime value) {
		_value = value;
	}

	public boolean IsExpired() {
		return this != INFINITE && _value.isBefore(LocalDateTime.now());
	}

	public static Result<ExpirationDate> Create(LocalDateTime value) {
		return Result.ok(new ExpirationDate(value));
	}
}
