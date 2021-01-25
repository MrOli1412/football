package com.pl.football.backend.repository.pzpn;

import com.pl.football.backend.model.pzpn.PzpnTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PZPNTeamRepository extends JpaRepository<PzpnTeam, UUID> {
}
