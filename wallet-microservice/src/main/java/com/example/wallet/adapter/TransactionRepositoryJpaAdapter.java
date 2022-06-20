package com.example.wallet.adapter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import com.example.wallet.domain.Identity;
import com.example.wallet.domain.Transaction;
import com.example.wallet.entity.TransactionEntity;
import com.example.wallet.repository.TransactionEntityRepository;
import com.example.wallet.repository.TransactionRepository;

@Repository
@ConditionalOnProperty(name = "persistence.target.jpa")
public class TransactionRepositoryJpaAdapter implements TransactionRepository {

	private final TransactionEntityRepository transanctionRepo;
	private final ModelMapper modelMapper;

	public TransactionRepositoryJpaAdapter(TransactionEntityRepository transanctionRepo, ModelMapper modelMapper) {
		this.transanctionRepo = transanctionRepo;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<Transaction> findByIdentity(Identity identity) {
		List<Transaction> list = new ArrayList<Transaction>();
		for(TransactionEntity entity : transanctionRepo.findByIdentity(identity.getValue())) {
			list.add(modelMapper.map(entity, Transaction.class));
		}
		return list;	 
	}

	@Override
	public Transaction persist(Transaction transaction) {
		transanctionRepo.save(modelMapper.map(transaction, TransactionEntity.class));
		return transaction;
	}



}
