package com.example.wallet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.wallet.entity.TransactionEntity;

public interface TransactionEntityRepository extends JpaRepository<TransactionEntity, Long> {
	
	List<TransactionEntity> findByIdentity(String identity);


}
