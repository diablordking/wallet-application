package com.example.wallet.service;

import java.util.List;

import com.example.wallet.dto.request.CreatePlayerRequest;
import com.example.wallet.dto.response.BalanceResponse;
import com.example.wallet.dto.response.CreatePlayerResponse;
import com.example.wallet.dto.response.PlayerResponse;
import com.example.wallet.dto.response.TransactionResponse;

public interface WalletService {

	PlayerResponse findPlayerById(String identity);

	BalanceResponse getPlayerBalance(String identity);

	List<TransactionResponse> getPlayerTransactions(String identity);

	CreatePlayerResponse createPlayer(CreatePlayerRequest request);

	PlayerResponse debitPlayer(String identity, double amount);

	PlayerResponse creditPlayer(String identity, double amount);

	PlayerResponse getPlayer(String identity);

}
