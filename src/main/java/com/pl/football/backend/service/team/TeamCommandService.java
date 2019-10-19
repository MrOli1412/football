package com.pl.football.backend.service.team;

import com.pl.football.backend.dto.team.TeamCreateDTO;
import com.pl.football.backend.dto.team.TeamQueryDTO;
import com.pl.football.backend.dto.team.TeamUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service

public interface TeamCommandService {
    UUID createTeam(TeamCreateDTO teamCreateDTO);

    TeamQueryDTO updateTeam(UUID id, TeamUpdateDTO teamUpdateDTO);
}
