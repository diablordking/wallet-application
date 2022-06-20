package com.example.wallet.application.business.exception;

import com.example.wallet.domain.Identity;

@SuppressWarnings("serial")
public class ExistingPlayerException extends Exception {
	private final Identity kimlikNo;

	public ExistingPlayerException(Identity kimlikNo) {
		super("Player with identity (%s) already exists.".formatted(kimlikNo.getValue()));
		this.kimlikNo = kimlikNo;
	}

	public Identity getKimlikNo() {
		return kimlikNo;
	}

}
