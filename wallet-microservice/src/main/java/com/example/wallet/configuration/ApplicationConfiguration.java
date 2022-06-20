package com.example.wallet.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.wallet.application.WalletApplication;
import com.example.wallet.application.business.StandartWalletApplication;
import com.example.wallet.repository.PlayerRepository;
import com.example.wallet.repository.TransactionRepository;

@Configuration
public class ApplicationConfiguration {

	@Bean
	public WalletApplication hrApp(PlayerRepository employeeRepository, TransactionRepository transactionRepository) {
		return new StandartWalletApplication(employeeRepository, transactionRepository);
	}
}
