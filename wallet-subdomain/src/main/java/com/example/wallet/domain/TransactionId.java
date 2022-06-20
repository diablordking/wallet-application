package com.example.wallet.domain;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.example.ddd.annotation.ValueObject;

@ValueObject
public final class TransactionId {
	private final long value;
	private static final Map<Long, TransactionId> cache = new ConcurrentHashMap<>();

	private TransactionId(long value) {
		this.value = value;
	}

	public long getValue() {
		return value;
	}

	public static TransactionId valueOf(long value) {
		// validation
		Objects.requireNonNull(value);
		if (!isValid(value))
			throw new IllegalArgumentException("This is not a valid identity no");
		// object pooling ?
		var transaction = cache.get(value);
		if (Objects.isNull(transaction)) {
			transaction = new TransactionId(value);
			cache.put(value, transaction);
		}
		return transaction;
	}

	private static boolean isValid(long value) {
		if (value < 0)
			return false;
		return true;
	}
}
