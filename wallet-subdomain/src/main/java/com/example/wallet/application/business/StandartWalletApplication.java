package com.example.wallet.application.business;

import java.util.List;

import com.example.wallet.application.WalletApplication;
import com.example.wallet.application.business.exception.ExistingPlayerException;
import com.example.wallet.application.business.exception.PlayerInsufficientBalanceException;
import com.example.wallet.application.business.exception.PlayerNotFoundException;
import com.example.wallet.domain.Amount;
import com.example.wallet.domain.Balance;
import com.example.wallet.domain.Identity;
import com.example.wallet.domain.Player;
import com.example.wallet.domain.Transaction;
import com.example.wallet.domain.TransactionType;
import com.example.wallet.repository.PlayerRepository;
import com.example.wallet.repository.TransactionRepository;

public class StandartWalletApplication implements WalletApplication {
	private PlayerRepository playerRepository;
	private TransactionRepository transactionRepository;

	public StandartWalletApplication(PlayerRepository employeeRepository, TransactionRepository transactionRepository) {
		this.playerRepository = employeeRepository;
		this.transactionRepository = transactionRepository;
	}

	@Override
	public Player createPlayer(Player employee) throws ExistingPlayerException {
		var kimlikNo = employee.getIdentity();
		var foundPlayer = playerRepository.findById(kimlikNo);
		if (foundPlayer.isPresent())
			throw new ExistingPlayerException(kimlikNo);
		return playerRepository.persist(employee);
	}

	@Override
	public Balance getPlayerBalance(Identity identity) throws PlayerNotFoundException {
		var foundPlayer = playerRepository.findById(identity).orElseThrow(() -> new PlayerNotFoundException(identity));
		return foundPlayer.getBalance();
	}

	@Override
	public List<Transaction> getPlayerTransactions(Identity identity) {
		var transactions = transactionRepository.findByIdentity(identity);
		return transactions;
	}

	@Override
	public Player debitPlayer(Identity kimlikNo, Amount amount)
			throws PlayerNotFoundException, PlayerInsufficientBalanceException {
		var foundPlayer = playerRepository.findById(kimlikNo).orElseThrow(() -> new PlayerNotFoundException(kimlikNo));
		if (foundPlayer.getBalance().getValue().compareTo(amount.getValue()) == -1) {
			throw new PlayerInsufficientBalanceException(kimlikNo);
		}
		foundPlayer.setBalance(Balance.valueOf(foundPlayer.getBalance().getValue().subtract(amount.getValue())));
		transactionRepository.persist(new Transaction.Builder(foundPlayer).amount(amount.getValue()).transactionType(TransactionType.DEBIT.name()).build());
		playerRepository.persist(foundPlayer);
		return foundPlayer;
	}

	@Override
	public Player creditPlayer(Identity kimlikNo, Amount amount) throws PlayerNotFoundException {
		var foundPlayer = playerRepository.findById(kimlikNo).orElseThrow(() -> new PlayerNotFoundException(kimlikNo));
		foundPlayer.setBalance(Balance.valueOf(foundPlayer.getBalance().getValue().add(amount.getValue())));
		transactionRepository.persist(new Transaction.Builder(foundPlayer).amount(amount.getValue()).transactionType(TransactionType.CREDIT.name()).build());
		playerRepository.persist(foundPlayer);
		return foundPlayer;
	}

	@Override
	public Player getPlayer(Identity identity) throws PlayerNotFoundException {
		var foundPlayer = playerRepository.findById(identity).orElseThrow(() -> new PlayerNotFoundException(identity));
		return foundPlayer;
	}

}
