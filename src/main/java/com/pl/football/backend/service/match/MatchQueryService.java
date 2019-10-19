package com.pl.football.backend.service.match;

import com.pl.football.backend.dto.match.MatchQueryDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface MatchQueryService {
    List<MatchQueryDTO> getAllMatch();

    MatchQueryDTO getMatchById(UUID id);
}
