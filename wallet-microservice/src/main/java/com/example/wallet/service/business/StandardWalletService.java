package com.example.wallet.service.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.wallet.application.WalletApplication;
import com.example.wallet.application.business.exception.ExistingPlayerException;
import com.example.wallet.application.business.exception.PlayerInsufficientBalanceException;
import com.example.wallet.application.business.exception.PlayerNotFoundException;
import com.example.wallet.domain.Amount;
import com.example.wallet.domain.Identity;
import com.example.wallet.domain.Player;
import com.example.wallet.domain.Transaction;
import com.example.wallet.dto.request.CreatePlayerRequest;
import com.example.wallet.dto.response.BalanceResponse;
import com.example.wallet.dto.response.CreatePlayerResponse;
import com.example.wallet.dto.response.PlayerResponse;
import com.example.wallet.dto.response.TransactionResponse;
import com.example.wallet.service.WalletService;

@Service
public class StandardWalletService implements WalletService {
	private final WalletApplication walletApplication;
	private final ModelMapper modelMapper;

	public StandardWalletService(WalletApplication walletApplication, ModelMapper modelMapper) {
		this.walletApplication = walletApplication;
		this.modelMapper = modelMapper;
	}

	@Override
	public PlayerResponse findPlayerById(String identity) {
		try {
			var player = walletApplication.getPlayer(Identity.valueOf(identity));
			return modelMapper.map(player, PlayerResponse.class);
		} catch (PlayerNotFoundException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public CreatePlayerResponse createPlayer(CreatePlayerRequest request) {
		var employee = modelMapper.map(request, Player.class);
		try {
			walletApplication.createPlayer(employee);
			return new CreatePlayerResponse("success");
		} catch (ExistingPlayerException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	@Override
	public PlayerResponse debitPlayer(String identity, double amount) {
		try {
			Player player = walletApplication.debitPlayer(Identity.valueOf(identity),Amount.valueOf(new BigDecimal(amount)));
			return modelMapper.map(player, PlayerResponse.class);
		} catch (PlayerNotFoundException | PlayerInsufficientBalanceException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	@Override
	public PlayerResponse creditPlayer(String identity, double amount) {
		try {
			Player player = walletApplication.creditPlayer(Identity.valueOf(identity),Amount.valueOf(new BigDecimal(amount)));
			return modelMapper.map(player, PlayerResponse.class);
		} catch (PlayerNotFoundException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	@Override
	public PlayerResponse getPlayer(String identity) {
		try {
			Player player = walletApplication.getPlayer(Identity.valueOf(identity));
			return modelMapper.map(player, PlayerResponse.class);
		} catch (PlayerNotFoundException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	@Override
	public BalanceResponse getPlayerBalance(String identity) {
		try {
			 Player player = walletApplication.getPlayer(Identity.valueOf(identity));
			return modelMapper.map(player, BalanceResponse.class);
		} catch (PlayerNotFoundException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	@Override
	public List<TransactionResponse> getPlayerTransactions(String identity) {
		List<Transaction> playerTransactions = walletApplication.getPlayerTransactions(Identity.valueOf(identity));
		List<TransactionResponse> transactionResponses=new ArrayList<>();
		for(Transaction transaction:playerTransactions) {
			transactionResponses.add(modelMapper.map(transaction, TransactionResponse.class));
		}
		return transactionResponses;
	}

}
