package com.example.wallet.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) 	
public class CreatePlayerResponse {

	private final String status;
	private final String reason;

	public CreatePlayerResponse(String status) {
		this.status = status;
		this.reason = null;
	}

	public CreatePlayerResponse(String status, String reason) {
		this.status = status;
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public String getReason() {
		return reason;
	}

}
