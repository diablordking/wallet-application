package com.example.wallet.repository;

import java.util.List;

import com.example.wallet.domain.Identity;
import com.example.wallet.domain.Transaction;

public interface TransactionRepository {

	List<Transaction> findByIdentity(Identity identity);

	Transaction persist(Transaction transaction);

}
