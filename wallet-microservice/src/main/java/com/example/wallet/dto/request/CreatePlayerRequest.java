package com.example.wallet.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.example.validation.Identity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString()
public class CreatePlayerRequest {
	@Identity
	private String identity;
	@Size(min = 3)
	private String firstName;
	@Size(min = 2)
	private String lastName;
	@Min(0)
	private double balance;
	@Max(2004)
	private int birthYear;

}
