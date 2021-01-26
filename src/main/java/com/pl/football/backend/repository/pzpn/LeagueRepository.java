package com.pl.football.backend.repository.pzpn;

import com.pl.football.backend.model.pzpn.League;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeagueRepository extends JpaRepository<League, Integer> {
    Optional<List<League>> findByStateId(Integer id);
}
