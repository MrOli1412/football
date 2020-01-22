package com.pl.football.backend.repository;

import com.pl.football.backend.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ClubRepository extends JpaRepository<Club, UUID> {
}
