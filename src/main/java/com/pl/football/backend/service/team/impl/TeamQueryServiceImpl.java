package com.pl.football.backend.service.team.impl;

import com.pl.football.backend.dto.team.TeamQueryDTO;
import com.pl.football.backend.repository.TeamRepository;
import com.pl.football.backend.service.team.TeamQueryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TeamQueryServiceImpl implements TeamQueryService {
    private final TeamRepository teamRepository;

    @Autowired
    public TeamQueryServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<TeamQueryDTO> getAllTeams() {
        return null;
    }

    @Override
    public TeamQueryDTO getTeamById(UUID id) {
        ModelMapper mapper = new ModelMapper();

        return mapper.map(teamRepository.getOne(id),TeamQueryDTO.class);
    }
}
