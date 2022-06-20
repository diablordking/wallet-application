package com.example.wallet.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.wallet.controller.WalletApplicationController;
import com.example.wallet.dto.request.CreatePlayerRequest;
import com.example.wallet.dto.request.CreditPlayerRequest;
import com.example.wallet.dto.request.DebitPlayerRequest;
import com.example.wallet.service.business.StandardWalletService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings(value = { "unused" })
@SpringBootTest(classes = { WalletServiceTest.class })
public class WalletServiceTest {

	@InjectMocks
	private WalletApplicationController applicationController;

	@Mock
	private StandardWalletService walletService;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		applicationController = new WalletApplicationController(walletService);
		mockMvc = MockMvcBuilders.standaloneSetup(applicationController).build();
	}

	@Test
	public void testCreatePlayer() throws Exception {
		CreatePlayerRequest request = new CreatePlayerRequest();

		request.setIdentity("30072159108");
		request.setFirstName("Alp");
		request.setLastName("Albay");
		request.setBalance(1);
		request.setBirthYear(1995);

		String json = new ObjectMapper().writeValueAsString(request);
		
		MvcResult mvcResult = mockMvc.perform(post("/player").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void testGetPlayer() throws Exception {
		CreatePlayerRequest request = new CreatePlayerRequest();

		request.setIdentity("30072159108");
		request.setFirstName("Alp");
		request.setLastName("Albay");
		request.setBalance(1);
		request.setBirthYear(1995);

		String json = new ObjectMapper().writeValueAsString(request);
		MvcResult mvcResult = mockMvc.perform(post("/player").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andReturn();

		MvcResult mvcResult2 = mockMvc.perform(get("/player/30072159108")).andExpect(status().isOk()).andReturn();
	}

	@Test
	public void testGetPlayerBalance() throws Exception {
		CreatePlayerRequest request = new CreatePlayerRequest();

		request.setIdentity("30072159108");
		request.setFirstName("Alp");
		request.setLastName("Albay");
		request.setBalance(1);
		request.setBirthYear(1995);

		String json = new ObjectMapper().writeValueAsString(request);
		MvcResult mvcResult = mockMvc.perform(post("/player").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andReturn();

		MvcResult mvcResult2 = mockMvc.perform(get("/player/balance/30072159108")).andExpect(status().isOk())
				.andReturn();
	}

	@Test
	public void testDebitPlayer() throws Exception {
		CreatePlayerRequest request = new CreatePlayerRequest();
		request.setIdentity("30072159108");
		request.setFirstName("Alp");
		request.setLastName("Albay");
		request.setBalance(1000);
		request.setBirthYear(1995);

		String json = new ObjectMapper().writeValueAsString(request);
		MvcResult mvcResult = mockMvc.perform(post("/player").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andReturn();

		DebitPlayerRequest debitPlayerRequest = new DebitPlayerRequest();

		debitPlayerRequest.setBalance(10);
		debitPlayerRequest.setIdentity("30072159108");
		String json2 = new ObjectMapper().writeValueAsString(debitPlayerRequest);

		MvcResult mvcResult2 = mockMvc
				.perform(post("/player/debit").contentType(MediaType.APPLICATION_JSON).content(json2))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void testCreditPlayer() throws Exception {
		CreatePlayerRequest request = new CreatePlayerRequest();
		request.setIdentity("30072159108");
		request.setFirstName("Alp");
		request.setLastName("Albay");
		request.setBalance(1000);
		request.setBirthYear(1995);

		String json = new ObjectMapper().writeValueAsString(request);
		MvcResult mvcResult = mockMvc.perform(post("/player").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andReturn();

		CreditPlayerRequest creditPlayerRequest = new CreditPlayerRequest();

		creditPlayerRequest.setBalance(10);
		creditPlayerRequest.setIdentity("30072159108");
		String json2 = new ObjectMapper().writeValueAsString(creditPlayerRequest);

		MvcResult mvcResult2 = mockMvc
				.perform(post("/player/credit").contentType(MediaType.APPLICATION_JSON).content(json2))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void testGetPlayerTransactions() throws Exception {
		CreatePlayerRequest request = new CreatePlayerRequest();
		request.setIdentity("30072159108");
		request.setFirstName("Alp");
		request.setLastName("Albay");
		request.setBalance(1000);
		request.setBirthYear(1995);

		String json = new ObjectMapper().writeValueAsString(request);
		MvcResult mvcResult = mockMvc.perform(post("/player").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andReturn();

		CreditPlayerRequest creditPlayerRequest = new CreditPlayerRequest();

		creditPlayerRequest.setBalance(10);
		creditPlayerRequest.setIdentity("30072159108");
		String json2 = new ObjectMapper().writeValueAsString(creditPlayerRequest);

		MvcResult mvcResult2 = mockMvc
				.perform(post("/player/credit").contentType(MediaType.APPLICATION_JSON).content(json2))
				.andExpect(status().isOk()).andReturn();

		MvcResult mvcResult3 = mockMvc.perform(get("/player/transaction/30072159108")).andExpect(status().isOk())
				.andReturn();
	}

}
