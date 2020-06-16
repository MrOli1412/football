package com.pl.football.backend.repository;

import com.pl.football.backend.model.Dress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository

public interface DressRepository extends JpaRepository<Dress, UUID> {

    Optional<Integer> countByTeam_Id(UUID teamId);
    Optional<List<Dress>> getByTeam_Id(UUID teamId);
}
