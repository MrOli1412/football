package com.pl.football.backend.service.match;

import com.pl.football.backend.dto.match.MatchCreateDTO;
import com.pl.football.backend.dto.match.MatchQueryDTO;
import com.pl.football.backend.dto.match.MatchUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface MatchCommandService {
    UUID createMatch(MatchCreateDTO  match);

    MatchQueryDTO updateMatch(UUID id, MatchUpdateDTO matchUpdateDTO);
}
