package com.example.wallet.service;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses(value = {
	WalletServiceTest.class
})
public class WalletServiceTestSuite {

}
