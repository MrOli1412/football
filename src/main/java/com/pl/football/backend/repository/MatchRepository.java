package com.pl.football.backend.repository;

import com.pl.football.backend.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository

public interface MatchRepository extends JpaRepository<Match, UUID> {
    Optional<Integer> countByTeam_Id(UUID id);

    Optional<List<Match>> getByTeam_Id(UUID id);
}
