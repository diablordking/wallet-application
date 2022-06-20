package com.example.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.wallet.dto.request.CreatePlayerRequest;
import com.example.wallet.dto.request.CreditPlayerRequest;
import com.example.wallet.dto.request.DebitPlayerRequest;
import com.example.wallet.dto.response.BalanceResponse;
import com.example.wallet.dto.response.CreatePlayerResponse;
import com.example.wallet.dto.response.PlayerResponse;
import com.example.wallet.dto.response.TransactionResponse;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@RestController
@RequestMapping("/wallet/player")
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
public class WalletController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private LoadBalancerClient lbClient;
	
	@Operation(summary = "Get player information by its identity")
	@GetMapping("{identity}")
	public PlayerResponse getPlayerByIdentity(@PathVariable String identity) { 
		PlayerResponse playerResponse=null;
		ServiceInstance si =lbClient.choose("WALLET");
		playerResponse=restTemplate.getForObject(si.getUri()+"/wallet/api/v1/player/"+identity, PlayerResponse.class);
		return playerResponse; 
	}
	
	@Operation(summary = "Create an player")
	@PostMapping()
	public CreatePlayerResponse createPlayer(@RequestBody CreatePlayerRequest request) {
		CreatePlayerResponse playerResponse=null;
		ServiceInstance si =lbClient.choose("WALLET");
		playerResponse=restTemplate.postForObject(si.getUri()+"/wallet/api/v1/player/", request, CreatePlayerResponse.class);
		return playerResponse; 
	}
	
	@Operation(summary = "Get balance of player")
	@GetMapping("balance/{identity}")
	public BalanceResponse getPlayerBalance(@PathVariable String identity) {
		BalanceResponse balanceResponse=null;
		ServiceInstance si =lbClient.choose("WALLET");
		balanceResponse=restTemplate.getForObject(si.getUri()+"/wallet/api/v1/player/balance/"+identity, BalanceResponse.class);
		return balanceResponse; 
	}
	@Operation(summary = "Debit wallet to player")
	@PostMapping("debit")
	public PlayerResponse debitPlayerBalance(@RequestBody DebitPlayerRequest request ) {
		PlayerResponse playerResponse=null;
		ServiceInstance si =lbClient.choose("WALLET");
		playerResponse=restTemplate.postForObject(si.getUri()+"/wallet/api/v1/player/debit", request, PlayerResponse.class);
		return playerResponse; 
	}
	@Operation(summary = "Credit wallet to player")
	@PostMapping("credit")
	public PlayerResponse creditPlayerBalance(@RequestBody  CreditPlayerRequest request ) {
		PlayerResponse playerResponse=null;
		ServiceInstance si =lbClient.choose("WALLET");
		playerResponse=restTemplate.postForObject(si.getUri()+"/wallet/api/v1/player/credit", request, PlayerResponse.class);
		return playerResponse; 
	}
	@Operation(summary = "Get Transactions of player")
	@GetMapping("transaction/{identity}")
	public TransactionResponse[] getPlayerTransactions(@PathVariable String identity) {
		TransactionResponse[]  transactionList=null;
		ServiceInstance si =lbClient.choose("WALLET");
		transactionList=restTemplate.getForObject(si.getUri()+"/wallet/api/v1/player/transaction/"+identity,TransactionResponse[].class );
		return transactionList; 
	}
	
	
}
