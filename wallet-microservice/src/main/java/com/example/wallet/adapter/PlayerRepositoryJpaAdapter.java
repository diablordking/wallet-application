package com.example.wallet.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.wallet.domain.Identity;
import com.example.wallet.domain.Player;
import com.example.wallet.entity.PlayerEntity;
import com.example.wallet.repository.PlayerEntityRepository;
import com.example.wallet.repository.PlayerRepository;

@Repository
@ConditionalOnProperty(name = "persistence.target.jpa")
public class PlayerRepositoryJpaAdapter implements PlayerRepository {
	private final PlayerEntityRepository playerRepo;
	private final ModelMapper modelMapper;

	public PlayerRepositoryJpaAdapter(PlayerEntityRepository playerRepo, ModelMapper modelMapper) {
		this.playerRepo = playerRepo;
		this.modelMapper = modelMapper;
	}

	@Override
	public Optional<Player> findById(Identity identity) {
		return playerRepo.findById(identity.getValue()).map(emp -> modelMapper.map(emp, Player.class));
	}

	@Override
	public Player persist(Player player) {
		playerRepo.save(modelMapper.map(player, PlayerEntity.class));
		return player;
	}

	@Override
	@Transactional
	public void remove(Player player) {
		playerRepo.delete(modelMapper.map(player, PlayerEntity.class));
	}

}
