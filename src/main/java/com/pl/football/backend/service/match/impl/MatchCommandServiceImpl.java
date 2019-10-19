package com.pl.football.backend.service.match.impl;

import com.pl.football.backend.dto.match.MatchCreateDTO;
import com.pl.football.backend.dto.match.MatchQueryDTO;
import com.pl.football.backend.dto.match.MatchUpdateDTO;
import com.pl.football.backend.repository.MatchRepository;
import com.pl.football.backend.service.match.MatchCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MatchCommandServiceImpl implements MatchCommandService {

    private final MatchRepository matchRepository;
    @Autowired
    public MatchCommandServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public UUID createMatch(MatchCreateDTO match) {
        return null;
    }

    @Override
    public MatchQueryDTO updateMatch(UUID id, MatchUpdateDTO matchUpdateDTO) {
        return null;
    }
}
