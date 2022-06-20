package com.example.wallet.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.validation.Identity;

import lombok.Data;

@Entity
@Table(name="players")
@Data
public class PlayerEntity {
	@Id
	@Identity
	private String identity;
	@Column(name="fname")
	private String firstName;
	@Column(name="lname")
	private String lastName;
	@Column(name="balance")
	private BigDecimal balance;
	@Column(name="birthyear")
	private int birthYear;

}
