package com.pl.football.backend.repository;

import com.pl.football.backend.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository

public interface PlayerRepository extends JpaRepository<Player, UUID> {
    boolean existsByEvidentialNumber(String evidentialNumber);

    Optional<Player> getPlayerByEvidentialNumber(String evidentialNumber);

    Optional<List<Player>> getByTeam_Id(UUID id);

    Optional<Integer> countByTeam_Id(UUID id);
}
