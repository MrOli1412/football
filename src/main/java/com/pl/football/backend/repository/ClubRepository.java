package com.pl.football.backend.repository;

import com.pl.football.backend.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClubRepository extends JpaRepository<Club, UUID> {
    Optional<Club> getById(UUID id);

    List<Club> findAll();

    boolean existsByClubName(String clubName);
}
