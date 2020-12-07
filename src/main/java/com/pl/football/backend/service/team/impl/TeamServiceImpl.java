package com.pl.football.backend.service.team.impl;

import com.pl.football.backend.dto.club.ClubQueryDTO;
import com.pl.football.backend.dto.team.TeamClubDTO;
import com.pl.football.backend.dto.team.TeamCreateDTO;
import com.pl.football.backend.dto.team.TeamShortDTO;
import com.pl.football.backend.dto.team.TeamUpdateDTO;
import com.pl.football.backend.exception.FootballException;
import com.pl.football.backend.model.Club;
import com.pl.football.backend.model.Team;
import com.pl.football.backend.repository.*;
import com.pl.football.backend.service.club.ClubService;
import com.pl.football.backend.service.team.TeamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;
    private final DressRepository dressRepository;
    private final ClubRepository clubRepository;


    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, PlayerRepository playerRepository, MatchRepository matchRepository, DressRepository dressRepository, ClubRepository clubRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
        this.matchRepository = matchRepository;
        this.dressRepository = dressRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    public List<TeamClubDTO> getAllTeams() {
        return null;
    }

    @Override
    public TeamClubDTO getTeamById(UUID id) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(teamRepository.getById(id).orElseThrow(() -> new FootballException("tak")), TeamClubDTO.class);

    }

    @Override
    public List<TeamClubDTO> getTeamsForClub(UUID clubId) {
        try {
            ModelMapper mapper = new ModelMapper();
            List<TeamClubDTO> result = new ArrayList<>();
            teamRepository.getByClub_Id(clubId).ifPresent(e -> {
                e.forEach(team -> {
                    result.add(mapper.map(team, TeamClubDTO.class));
                });
            });
            return result;
        } catch (Exception ex) {
            throw new FootballException(HttpStatus.BAD_REQUEST, "Error in get team for club" + ex.getMessage());
        }
    }

    @Override
    public TeamShortDTO getShortInfo(UUID id) {
        try {
            TeamShortDTO teamShortDTO = new TeamShortDTO();
            teamShortDTO.setCountDress(this.dressRepository.countByTeam_Id(id).orElse(0));
            teamShortDTO.setCountMatches(this.matchRepository.countByTeam_Id(id).orElse(0));
            teamShortDTO.setCountPlayers(this.playerRepository.countByTeam_Id(id).orElse(0));
            teamShortDTO.setTeamName(getTeamById(id).getTeamName());
            return teamShortDTO;
        } catch (Exception ex) {
            throw new FootballException(HttpStatus.BAD_REQUEST, "Error in get short team info" + ex.getMessage());
        }
    }

    @Override
    public UUID createTeam(TeamCreateDTO teamCreateDTO, UUID clubId) {
        if (teamRepository.existsByTeamNameAndClub_Id(teamCreateDTO.getTeamName(), clubId)) {
            throw new FootballException("Object exist");
        } else {

            ModelMapper mapper = new ModelMapper();
            Team team = mapper.map(teamCreateDTO, Team.class);
            Club map  = clubRepository.getById(clubId).orElseThrow(() -> new FootballException("Club does not exist"));
            team.setClub(map);
            return teamRepository.save(team).getId();
        }

    }

    @Override
    public TeamClubDTO updateTeam(UUID id, TeamUpdateDTO teamUpdateDTO) {
        Team team = teamRepository.findById(id).orElseThrow(()->new FootballException("team does not exist"));
        try {
            //TODO Co można zmienić w drużynie?

            ModelMapper modelMapper = new ModelMapper();

            team = teamRepository.saveAndFlush(team);
            return modelMapper.map(team, TeamClubDTO.class);
        } catch (Exception ex) {
            throw new FootballException(HttpStatus.BAD_REQUEST, "Error in updating team" + ex.getMessage());
        }

    }

}
