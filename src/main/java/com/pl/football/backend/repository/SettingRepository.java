package com.pl.football.backend.repository;

import com.pl.football.backend.model.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository

public interface SettingRepository extends JpaRepository<Setting, UUID> {
}
