package com.library.domain.users;

import java.time.LocalDate;

import org.apache.commons.lang3.Validate;

import com.core.ddd.ValueObject;

public class ExpiryDate extends ValueObject {
	private static final long serialVersionUID = 1L;
	private LocalDate _value;

	public static final ExpiryDate DEFAULT = ExpiryDate.from(LocalDate.now().plusWeeks(2));

	private ExpiryDate(LocalDate value) {
		_value = value;
	}

	public LocalDate getValue() {
		return _value;
	}

	public boolean isExpired() {
		return _value.compareTo(LocalDate.now()) < 0;
	}

	public static ExpiryDate from(final LocalDate date) {
		Validate.notNull(date, "date cannot be null");
		return new ExpiryDate(date);
	}
}
