package com.pl.football.backend.service.pzpn.impl;

import com.pl.football.backend.dto.match.MatchShortInfoDTO;
import com.pl.football.backend.dto.pzpn.LeagueDTO;
import com.pl.football.backend.dto.pzpn.PzpnTeamDTO;
import com.pl.football.backend.exception.FootballException;
import com.pl.football.backend.model.pzpn.League;
import com.pl.football.backend.model.pzpn.PzpnTeam;
import com.pl.football.backend.model.pzpn.State;
import com.pl.football.backend.repository.pzpn.LeagueRepository;
import com.pl.football.backend.repository.pzpn.PZPNTeamRepository;
import com.pl.football.backend.repository.pzpn.StateRepository;
import com.pl.football.backend.service.pzpn.PzpnService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PzpnServiceImpl implements PzpnService {
    private final LeagueRepository leagueRepository;
    private final StateRepository stateRepository;
    private final PZPNTeamRepository pzpnTeamRepository;

    public PzpnServiceImpl(LeagueRepository leagueRepository, StateRepository stateRepository, PZPNTeamRepository pzpnTeamRepository) {
        this.leagueRepository = leagueRepository;
        this.stateRepository = stateRepository;
        this.pzpnTeamRepository = pzpnTeamRepository;
    }

    @Override
    public List<State> getStates() {

        return stateRepository.findAll();

    }

    @Override
    public List<LeagueDTO> getLeagueByStateId(Integer id)  {

        try {
            List<LeagueDTO> response = new ArrayList<>();
            ModelMapper modelMapper = new ModelMapper();
            leagueRepository.findByStateId(id).ifPresent(leagues ->
                    leagues.forEach(league ->
                            response.add(modelMapper.map(league, LeagueDTO.class))));
            return response;
        } catch (Exception ex) {
            throw new FootballException(HttpStatus.BAD_REQUEST, "Error in get match list for team" + ex.getMessage());
        }

    }

    @Override
    public List<PzpnTeamDTO> getTeamsByLeagueId(Integer id) {
        try {
            List<PzpnTeamDTO> response = new ArrayList<>();
            ModelMapper mapper = new ModelMapper();
            pzpnTeamRepository.findByLeague_Id(id).ifPresent(pzpnTeams -> pzpnTeams.forEach(pzpnTeam -> {
                response.add(mapper.map(pzpnTeam, PzpnTeamDTO.class));
            }));
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new FootballException(HttpStatus.BAD_REQUEST, "Error in get match list for team" + ex.getMessage());

        }
    }
}
