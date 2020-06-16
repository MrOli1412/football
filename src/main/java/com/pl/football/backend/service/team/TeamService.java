package com.pl.football.backend.service.team;

import com.pl.football.backend.dto.team.TeamClubDTO;
import com.pl.football.backend.dto.team.TeamCreateDTO;
import com.pl.football.backend.dto.team.TeamShortDTO;
import com.pl.football.backend.dto.team.TeamUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface TeamService {
    UUID createTeam(TeamCreateDTO teamCreateDTO, UUID clubId);

    TeamClubDTO updateTeam(UUID id, TeamUpdateDTO teamUpdateDTO);

    List<TeamClubDTO> getAllTeams();

    TeamClubDTO getTeamById(UUID id);

    List<TeamClubDTO> getTeamsForClub(UUID clubId);

    TeamShortDTO getShortInfo(UUID id);
}
