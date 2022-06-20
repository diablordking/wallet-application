package com.example.wallet.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.validation.Identity;
import com.example.wallet.dto.request.CreatePlayerRequest;
import com.example.wallet.dto.request.CreditPlayerRequest;
import com.example.wallet.dto.request.DebitPlayerRequest;
import com.example.wallet.dto.response.BalanceResponse;
import com.example.wallet.dto.response.CreatePlayerResponse;
import com.example.wallet.dto.response.PlayerResponse;
import com.example.wallet.dto.response.TransactionResponse;
import com.example.wallet.service.WalletService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@RestController
//@RequestScope
@RequestMapping("/player")
@Validated
@CrossOrigin
@OpenAPIDefinition(
		info = @Info(
			title = "Wallet RESTful Service", 
			version = "1.0",
			description = "Wallet REST API enables clients to access wallet sub-domain capabilities through http.",
			license = @License(name = "MIT License"),
			contact = @Contact(email = "mhtalpalbayraktaroglu@gmail.com",name = "Mehmet Alp Albayraktaroğlu")
		)
)
public class WalletApplicationController {
	private WalletService walletService;
	
	public WalletApplicationController(WalletService walletService) {
		this.walletService = walletService;
		System.err.println(walletService.getClass());
	}
	
	@Operation(summary = "Get player information by its identity")
	@GetMapping("{identity}")
	public PlayerResponse getPlayerByIdentity(@PathVariable @Identity String identity) {
		return walletService.findPlayerById(identity);
	}
	
	@Operation(summary = "Create an player")
	@PostMapping
	public CreatePlayerResponse createPlayer(@RequestBody @Validated CreatePlayerRequest request) {
		return walletService.createPlayer(request);
	}
	
	@Operation(summary = "Get balance of player")
	@GetMapping("balance/{identity}")
	public BalanceResponse getPlayerBalance(@PathVariable String identity) {
		return walletService.getPlayerBalance(identity);
	}
	
	@Operation(summary = "Debit wallet to player")
	@PostMapping("debit")
	public PlayerResponse debitPlayerBalance(@RequestBody @Validated DebitPlayerRequest request ) {
		return walletService.debitPlayer(request.getIdentity(),request.getBalance());
	}
	
	@Operation(summary = "Credit wallet to player")
	@PostMapping("credit")
	public PlayerResponse creditPlayerBalance(@RequestBody @Validated CreditPlayerRequest request ) {
		return walletService.creditPlayer(request.getIdentity(),request.getBalance());
	}
	
	@Operation(summary = "Get Transactions of player")
	@GetMapping("transaction/{identity}")
	public List<TransactionResponse> getPlayerTransactions(@PathVariable String identity) {
		return walletService.getPlayerTransactions(identity);
		}
}
