package com.pl.football.backend.service.team.impl;

import com.pl.football.backend.dto.team.TeamCreateDTO;
import com.pl.football.backend.dto.team.TeamQueryDTO;
import com.pl.football.backend.dto.team.TeamUpdateDTO;
import com.pl.football.backend.repository.TeamRepository;
import com.pl.football.backend.service.team.TeamCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TeamCommandServiceImpl implements TeamCommandService {

    private final TeamRepository teamRepository;

    public TeamCommandServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public UUID createTeam(TeamCreateDTO teamCreateDTO) {
        return null;
    }

    @Override
    public TeamQueryDTO updateTeam(UUID id, TeamUpdateDTO teamUpdateDTO) {
        return null;
    }
}
