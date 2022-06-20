package com.example.wallet.dto.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString()
@JsonInclude(JsonInclude.Include.NON_NULL) 	

public class TransactionResponse {
	private long transactionId;
	private String identity;
	private BigDecimal amount;
	private String transactionType;
}
