package com.pl.football.backend.service.match;

import com.pl.football.backend.dto.match.MatchQueryDTO;
import com.pl.football.backend.dto.match.MatchReportDTO;
import com.pl.football.backend.dto.match.MatchShortInfoDTO;
import com.pl.football.backend.dto.match.MatchUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface MatchService {
    UUID createMatch(UUID teamId, MatchReportDTO match);

    MatchQueryDTO updateMatch(UUID id, MatchUpdateDTO matchUpdateDTO);

    List<MatchQueryDTO> getAllMatch();

    List<MatchShortInfoDTO> getMatchByTeamId(UUID id);

    Integer countMatchesFromTeam(UUID teamId);

    MatchQueryDTO getInfoToFrom(UUID id);

}
