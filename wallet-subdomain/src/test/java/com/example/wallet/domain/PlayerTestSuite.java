package com.example.wallet.domain;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses(value = {
	PlayerTest.class,
	IdentityTest.class
})
public class PlayerTestSuite {

}
