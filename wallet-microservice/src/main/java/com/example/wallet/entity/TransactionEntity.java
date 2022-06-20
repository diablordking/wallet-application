package com.example.wallet.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.validation.Identity;
import com.example.wallet.domain.TransactionType;

import lombok.Data;

@Entity
@Table(name="transactions")
@Data
public class TransactionEntity {
	@Id
	@Column(name="transaction_id")
    @GeneratedValue
	private long transacitionId;
	@Identity
	@Column(name="identity")
	private String identity;
	@Column(name="amount")
	private BigDecimal amount;
	@Column(name="transaction_type")
	@Enumerated(EnumType.ORDINAL)
	private TransactionType transactionType;
}
