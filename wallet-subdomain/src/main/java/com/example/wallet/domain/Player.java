package com.example.wallet.domain;

import java.math.BigDecimal;

import com.example.ddd.annotation.Aggregate;
import com.example.ddd.annotation.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// Ubiquitous Language: Employee, TcKimlikNo, FullName, Iban, Money, ...
// HR-subdomain -> Bounded-Context
// Shared Kernel -> TcKimlikNo, Iban -> Shared Library (Maven)
// Entity Class 
//  i) identity -> identity
// ii) mutable
@Entity(identity = { "identity" })
@Aggregate
@Getter
@Setter
@ToString()
@EqualsAndHashCode(of = "identity")
public class Player {
	private final Identity identity;
	private FullName fullName;
	private Balance balance;
	private final BirthYear birthYear;

	public Player(Builder builder) {
		this.identity = builder.identity;
		this.fullName = builder.fullName;
		this.balance = builder.balance;
		this.birthYear = builder.birthYear;
	}

	public static class Builder {
		private Identity identity;
		private FullName fullName;
		private Balance balance;
		private BirthYear birthYear;

		public Builder(String identity) {
			this.identity = Identity.valueOf(identity);
		}

		public Builder fullName(String firstName, String lastName) {
			this.fullName = FullName.of(firstName, lastName);
			return this;
		}

		public Builder fullName(FullName fullName) {
			this.fullName = fullName;
			return this;
		}

		public Builder balance(BigDecimal value, FiatCurrency currency) {
			this.balance = Balance.valueOf(value, currency);
			return this;
		}

		public Builder salary(BigDecimal value) {
			return balance(value, FiatCurrency.TL);
		}

		public Builder birthYear(int value) {
			this.birthYear = new BirthYear(value);
			return this;
		}

		public Player build() {
			// Constraint
			// Validation
			// Business Rule
			// Invariants
			return new Player(this);
		}

	}

}
