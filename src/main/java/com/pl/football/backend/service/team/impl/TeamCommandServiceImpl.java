package com.pl.football.backend.service.team.impl;

import com.pl.football.backend.dto.club.ClubQueryDTO;
import com.pl.football.backend.dto.team.TeamCreateDTO;
import com.pl.football.backend.dto.team.TeamQueryDTO;
import com.pl.football.backend.dto.team.TeamUpdateDTO;
import com.pl.football.backend.model.Club;
import com.pl.football.backend.model.Team;
import com.pl.football.backend.repository.TeamRepository;
import com.pl.football.backend.service.club.ClubQueryService;
import com.pl.football.backend.service.team.TeamCommandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TeamCommandServiceImpl implements TeamCommandService {

    private final TeamRepository teamRepository;
    private final ClubQueryService clubQueryService;

    public TeamCommandServiceImpl(TeamRepository teamRepository, ClubQueryService clubQueryService) {
        this.teamRepository = teamRepository;
        this.clubQueryService = clubQueryService;
    }

    @Override
    public UUID createTeam(TeamCreateDTO teamCreateDTO) {
        ModelMapper mapper = new ModelMapper();
        Team team = mapper.map(teamCreateDTO, Team.class);
        ClubQueryDTO clubById = clubQueryService.getClubById(teamCreateDTO.getClub());
        Club map = mapper.map(clubById, Club.class);
        team.setClub(map);
        return teamRepository.save(team).getId();

    }

    @Override
    public TeamQueryDTO updateTeam(UUID id, TeamUpdateDTO teamUpdateDTO) {
        return null;
    }
}
