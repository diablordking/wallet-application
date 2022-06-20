package com.example.wallet.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString()
public class DebitPlayerRequest {
	private String identity;
	private double balance;
}
