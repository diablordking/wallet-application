package com.example.wallet.application.business.exception;

import com.example.wallet.domain.Identity;

@SuppressWarnings("serial")
public class PlayerNotFoundException extends Exception {
	private final Identity kimlikNo;

	public PlayerNotFoundException(Identity kimlikNo) {
		super("Player with identity (%s) does not exist.".formatted(kimlikNo.getValue()));
		this.kimlikNo = kimlikNo;
	}

	public Identity getKimlikNo() {
		return kimlikNo;
	}

}
