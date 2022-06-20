package com.example.wallet.application;

import java.util.List;

import com.example.wallet.application.business.exception.ExistingPlayerException;
import com.example.wallet.application.business.exception.PlayerInsufficientBalanceException;
import com.example.wallet.application.business.exception.PlayerNotFoundException;
import com.example.wallet.domain.Amount;
import com.example.wallet.domain.Balance;
import com.example.wallet.domain.Identity;
import com.example.wallet.domain.Player;
import com.example.wallet.domain.Transaction;

public interface WalletApplication {

	List<Transaction> getPlayerTransactions(Identity identity);

	Player createPlayer(Player employee) throws ExistingPlayerException;

	Player debitPlayer(Identity kimlikNo, Amount amount)
			throws PlayerNotFoundException, PlayerInsufficientBalanceException;

	Player creditPlayer(Identity kimlikNo, Amount amount) throws PlayerNotFoundException;

	Player getPlayer(Identity identity) throws PlayerNotFoundException;
	
	Balance getPlayerBalance(Identity identity) throws PlayerNotFoundException;

}
