package com.example.wallet.repository;

import java.util.Optional;

import com.example.wallet.domain.Identity;
import com.example.wallet.domain.Player;

public interface PlayerRepository {

	Optional<Player> findById(Identity kimlikNo);

	Player persist(Player player);

	void remove(Player foundEmployee);


}
