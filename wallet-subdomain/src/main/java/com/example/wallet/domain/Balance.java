package com.example.wallet.domain;

import java.math.BigDecimal;
import java.util.Objects;

import com.example.ddd.annotation.ValueObject;

@ValueObject
public class Balance {
	private final BigDecimal value;
	private final FiatCurrency currency;

	private Balance(BigDecimal value, FiatCurrency currency) {
		this.value = value;
		this.currency = currency;
	}

	private Balance(BigDecimal value) {
		this(value, FiatCurrency.TL);
	}

	public BigDecimal getValue() {
		return value;
	}

	public FiatCurrency getCurrency() {
		return currency;
	}

	public static Balance valueOf(BigDecimal value) {
		return valueOf(value, FiatCurrency.TL);
	}

	public static Balance valueOf(BigDecimal value, FiatCurrency currency) {
		if (Objects.isNull(currency))
			throw new IllegalArgumentException("Currenct must be a non null value.");
		if (value.compareTo(new BigDecimal(0)) == -1)
			throw new IllegalArgumentException("Money value must be positive.");
		return new Balance(value, currency);
	}
}
