package com.library.domain.customers;

import java.math.BigDecimal;

import com.core.ddd.ValueObject;
import com.core.utils.Result;

public class Dollars extends ValueObject {

	private static final long serialVersionUID = 1L;

	private static final BigDecimal MAX_DOLLAR_AMOUNT = new BigDecimal(1000000);
	protected static final BigDecimal ZERO = new BigDecimal(0);
	private BigDecimal _value;

	private Dollars(long value) {
		_value = new BigDecimal(value);
	}

	public BigDecimal getValue() {
		return _value;
	}

	public static Result<Dollars> Create(long dollarAmount) {
		if (dollarAmount < 0)
			return Result.error("Dollar amount cannot be negative");

		if (dollarAmount > MAX_DOLLAR_AMOUNT.longValue())
			return Result.error("Dollar amount cannot be greater than " + MAX_DOLLAR_AMOUNT);

		return Result.ok(new Dollars(dollarAmount));
	}

	public boolean isZero() {
		return _value.compareTo(ZERO) == 0;
	}

	public Dollars add(Dollars dollars) {
		Dollars result = new Dollars(_value.longValue());
		result._value = _value.add(dollars._value);
		return result;
	}
}
