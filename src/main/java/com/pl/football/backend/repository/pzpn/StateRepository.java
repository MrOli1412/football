package com.pl.football.backend.repository.pzpn;

import com.pl.football.backend.model.pzpn.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State,Integer> {
}
