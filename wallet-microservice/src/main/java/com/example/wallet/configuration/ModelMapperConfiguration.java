package com.example.wallet.configuration;

import java.math.BigDecimal;
import java.util.Objects;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.wallet.domain.FiatCurrency;
import com.example.wallet.domain.Player;
import com.example.wallet.domain.Transaction;
import com.example.wallet.dto.request.CreatePlayerRequest;
import com.example.wallet.dto.response.BalanceResponse;
import com.example.wallet.dto.response.PlayerResponse;
import com.example.wallet.dto.response.TransactionResponse;
import com.example.wallet.entity.PlayerEntity;
import com.example.wallet.entity.TransactionEntity;

@Configuration
public class ModelMapperConfiguration {

	private Converter<Player, PlayerResponse> PLAYER_TO_PLAYER_RESPONSE_CONVERTER = context -> {
		var employee = context.getSource();
		var response = new PlayerResponse();
		response.setIdentity(employee.getIdentity().getValue());
		response.setFirstName(employee.getFullName().firstName());
		response.setLastName(employee.getFullName().lastName());
		response.setBalance(employee.getBalance().getValue());
		response.setBirthYear(employee.getBirthYear().value());
		return response;
	};

	private Converter<Player, PlayerEntity> PLAYER_TO_PLAYER_ENTITY_CONVERTER = context -> {
		var employee = context.getSource();
		var employeeEntity = new PlayerEntity();
		employeeEntity.setIdentity(employee.getIdentity().getValue());
		employeeEntity.setFirstName(employee.getFullName().firstName());
		employeeEntity.setLastName(employee.getFullName().lastName());
		employeeEntity.setBalance(employee.getBalance().getValue());
		employeeEntity.setBirthYear(employee.getBirthYear().value());
		return employeeEntity;
	};

	private Converter<Player, BalanceResponse> BALANCE_TO_BALANCE_RESPONSE_ENTITY_CONVERTER = context -> {
		var balance = context.getSource();
		var balanceResponse = new BalanceResponse();
		balanceResponse.setBalance(balance.getBalance().getValue());
		balanceResponse.setIdentity(balance.getIdentity().getValue());
		return balanceResponse;
	};

	private Converter<CreatePlayerRequest, Player> CREATE_PLAYER_REQUEST_TO_EMPLOYEE_CONVERTER = context -> {
		var request = context.getSource();
		return new Player.Builder(request.getIdentity()).fullName(request.getFirstName(), request.getLastName())
				.balance(new BigDecimal(request.getBalance()), FiatCurrency.TL).birthYear(request.getBirthYear())
				.build();
	};

	private Converter<PlayerEntity, Player> PLAYER_ENTITY_TO_PLAYER_CONVERTER = context -> {
		var entity = context.getSource();
		return new Player.Builder(entity.getIdentity()).fullName(entity.getFirstName(), entity.getLastName())
				.balance(new BigDecimal(entity.getBalance().doubleValue()), FiatCurrency.TL)
				.birthYear(entity.getBirthYear()).build();
	};

	private Converter<Transaction, TransactionResponse> TRANSACTION_ENTITY_TO_LIST_TRANSACTION_CONVERTER = context -> {
		var entity = context.getSource();
		var transactionResponse = new TransactionResponse();
		transactionResponse.setAmount(entity.getAmount().getValue());
		transactionResponse.setIdentity(entity.getPlayer().getIdentity().getValue());
		transactionResponse.setTransactionId(entity.getTransactionId().getValue());
		transactionResponse.setTransactionType(entity.getTransactionType().name());
		return transactionResponse;
	};

	private Converter<Transaction, TransactionEntity> TRANSACTION_TO_TRANSACTION_ENTITY_CONVERTER = context -> {
		var entity = context.getSource();
		var transactionEntity = new TransactionEntity();
		transactionEntity.setTransactionType(entity.getTransactionType());
		transactionEntity.setAmount(entity.getAmount().getValue());
		transactionEntity.setIdentity(entity.getPlayer().getIdentity().getValue());
		if (Objects.nonNull(entity.getTransactionId())) {
			transactionEntity.setTransacitionId(entity.getTransactionId().getValue());
		}
		return transactionEntity;
	};
	private Converter<TransactionEntity, Transaction> TRANSACTION_ENTITY_TO_TRANSACTION_CONVERTER = context -> {
		var entity = context.getSource();
		var transactionEntity = new Transaction.Builder(new Player.Builder(entity.getIdentity()).build())
				.amount(entity.getAmount()).transactionId(entity.getTransacitionId())
				.transactionType(entity.getTransactionType().name()).build();
		return transactionEntity;
	};

	@Bean
	public ModelMapper mapper() {
		var mapper = new ModelMapper();
		mapper.addConverter(PLAYER_TO_PLAYER_RESPONSE_CONVERTER, Player.class, PlayerResponse.class);
		mapper.addConverter(CREATE_PLAYER_REQUEST_TO_EMPLOYEE_CONVERTER, CreatePlayerRequest.class, Player.class);
		mapper.addConverter(PLAYER_TO_PLAYER_ENTITY_CONVERTER, Player.class, PlayerEntity.class);
		mapper.addConverter(PLAYER_ENTITY_TO_PLAYER_CONVERTER, PlayerEntity.class, Player.class);
		mapper.addConverter(BALANCE_TO_BALANCE_RESPONSE_ENTITY_CONVERTER, Player.class, BalanceResponse.class);
		mapper.addConverter(TRANSACTION_ENTITY_TO_LIST_TRANSACTION_CONVERTER, Transaction.class,
				TransactionResponse.class);
		mapper.addConverter(TRANSACTION_TO_TRANSACTION_ENTITY_CONVERTER, Transaction.class, TransactionEntity.class);
		mapper.addConverter(TRANSACTION_ENTITY_TO_TRANSACTION_CONVERTER, TransactionEntity.class, Transaction.class);

		return mapper;
	}
}
