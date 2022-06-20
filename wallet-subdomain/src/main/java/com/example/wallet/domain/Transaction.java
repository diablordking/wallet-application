package com.example.wallet.domain;

import java.math.BigDecimal;

import com.example.ddd.annotation.Aggregate;
import com.example.ddd.annotation.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(identity = { "transactionId" })
@Aggregate
@Getter
@Setter
@ToString()
@EqualsAndHashCode(of = "transactionId")
public class Transaction {

	private TransactionId transactionId;
	private Player player;
	private Amount amount;
	private TransactionType transactionType;

	public Transaction(Builder builder) {

		this.player = builder.player;
		this.amount = builder.amount;
		this.transactionId = builder.transactionId;
		this.transactionType=builder.transactionType;
	}

	public static class Builder {
		private TransactionId transactionId;
		private Player player;
		private Amount amount;
		private TransactionType transactionType;

		public Builder transactionId(long transactionId) {
			this.transactionId = TransactionId.valueOf(transactionId);
			return this;
		}

		public Builder(Player Player) {
			this.player = Player;
		}

		public Builder amount(BigDecimal value, FiatCurrency currency) {
			this.amount = Amount.valueOf(value, currency);
			return this;
		}

		public Builder amount(BigDecimal value) {
			return amount(value, FiatCurrency.TL);
		}
		
		public Builder transactionType(String value) {
			this.transactionType= TransactionType.valueOf(value);
			return this;
		}

		public Transaction build() {
			// Constraint
			// Validation
			// Business Rule
			// Invariants
			return new Transaction(this);
		}

	}

}
