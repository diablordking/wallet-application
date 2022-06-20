package com.example.wallet.dto.request;

import javax.validation.constraints.Min;

import com.example.validation.Identity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString()
public class DebitPlayerRequest {
	@Identity
	private String identity;
	@Min(0)
	private double balance;
}
