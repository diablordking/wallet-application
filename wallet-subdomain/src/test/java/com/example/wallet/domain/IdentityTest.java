package com.example.wallet.domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class IdentityTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/resources/tckimlikno-valid.csv")
	void validTcKimlikNoTest(String value) {
		Identity kimlik = Identity.valueOf(value);
		assertAll(() -> assertNotNull(kimlik), () -> assertEquals(value, kimlik.getValue()));
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/resources/tckimlikno-invalid.csv")
	void invalidTcKimlikNoTest(String value) {
		assertThrows(IllegalArgumentException.class, () -> Identity.valueOf(value));
	}

}
