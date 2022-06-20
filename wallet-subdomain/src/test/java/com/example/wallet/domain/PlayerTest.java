package com.example.wallet.domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class PlayerTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/resources/players.csv")
	void createEmployeeSuccessfully(String identity,String firstName,String lastName,
			String iban,BigDecimal salary,String currency,String jobStyle,int birthYear,String department,String base64Photo) {
	    // 1. Test Setup/Fixture -> CUT
		// 2. Call Exercise Method -> MUT
		var player = new Player.Builder(identity)
				               .fullName(firstName, lastName)
				               .balance(salary, FiatCurrency.valueOf(currency))
				               .birthYear(birthYear)
				               .build();
		// 3. Verification
		assertAll(
			() -> assertEquals(identity, player.getIdentity().getValue()),
			() -> assertEquals(firstName, player.getFullName().firstName()),
			() -> assertEquals(lastName, player.getFullName().lastName()),
			() -> assertEquals(salary, player.getBalance().getValue()),
			() -> assertEquals(currency, player.getBalance().getCurrency().name()),
			() -> assertEquals(birthYear, player.getBirthYear().value())
		);
		// 4. Tear-down
	}

}
