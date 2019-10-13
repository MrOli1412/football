package com.pl.football.backend.repository;

import com.pl.football.backend.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository

public interface PlayerRepository extends JpaRepository<Player, UUID> {
}
