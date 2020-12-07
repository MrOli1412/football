package com.pl.football.backend.repository;

import com.pl.football.backend.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository

public interface TeamRepository extends JpaRepository<Team, UUID> {
    Optional<List<Team>> getByClub_Id(UUID uuid);

    Optional<Team> getById(UUID id);

    boolean existsByTeamNameAndClub_Id(String teamName, UUID id);
}
