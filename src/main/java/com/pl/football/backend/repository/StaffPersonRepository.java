package com.pl.football.backend.repository;

import com.pl.football.backend.model.StaffPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StaffPersonRepository extends JpaRepository<StaffPerson, UUID> {

    Optional<List<StaffPerson>> getByTeamId(UUID id);
}
