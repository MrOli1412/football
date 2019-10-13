package com.pl.football.backend.repository;

import com.pl.football.backend.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository

public interface TeamRepository extends JpaRepository<Team, UUID> {
}
