package com.example.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.wallet.entity.PlayerEntity;

public interface PlayerEntityRepository extends JpaRepository<PlayerEntity, String>{

}
