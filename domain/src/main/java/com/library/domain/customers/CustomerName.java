package com.library.domain.customers;

import com.core.utils.Result;

public class CustomerName {
	private static final long serialVersionUID = 1L;

	private String _value;
	private static final int MAX_LENGTH = 100;

	private CustomerName(String value) {
		_value = value;
	}

	public static Result<CustomerName> Create(String value) {
		value = value.trim();

		if (value.isEmpty())
			return Result.error("Customer name should not be empty");

		if (value.length() > MAX_LENGTH)
			return Result.error("Customer name cannot be more than -" + MAX_LENGTH);

		return Result.ok(new CustomerName(value));
	}
}
