package com.pl.football.backend.service.team;

import com.pl.football.backend.dto.team.TeamQueryDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface TeamQueryService {
    List<TeamQueryDTO> getAllTeams();

    TeamQueryDTO getTeamById(UUID id);

}
