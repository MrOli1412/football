package com.pl.football.backend.repository;

import com.pl.football.backend.model.Role;
import com.pl.football.backend.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findByName(RoleName roleName);
}
