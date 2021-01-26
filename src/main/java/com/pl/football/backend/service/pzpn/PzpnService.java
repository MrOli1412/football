package com.pl.football.backend.service.pzpn;

import com.pl.football.backend.dto.pzpn.LeagueDTO;
import com.pl.football.backend.dto.pzpn.PzpnTeamDTO;
import com.pl.football.backend.model.pzpn.League;
import com.pl.football.backend.model.pzpn.PzpnTeam;
import com.pl.football.backend.model.pzpn.State;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface PzpnService {

    List<State> getStates();

    List<LeagueDTO> getLeagueByStateId(Integer id) throws Exception;

    List<PzpnTeamDTO> getTeamsByLeagueId(Integer id) throws Exception;
}
