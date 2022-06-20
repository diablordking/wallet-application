package com.example.wallet.application.business.exception;

import com.example.wallet.domain.Identity;
@SuppressWarnings("serial")
public class PlayerInsufficientBalanceException extends Exception  {
	private final Identity kimlikNo;

	public PlayerInsufficientBalanceException(Identity kimlikNo) {
		super("Player with identity (%s) does not sufficient balance.".formatted(kimlikNo.getValue()));
		this.kimlikNo = kimlikNo;
	}

	public Identity getKimlikNo() {
		return kimlikNo;
	}

}
