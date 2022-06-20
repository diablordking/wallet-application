package com.example.wallet.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString()
public class CreatePlayerRequest {
	private String identity;
	private String firstName;
	private String lastName;
	private double balance;
	private int birthYear;

}
