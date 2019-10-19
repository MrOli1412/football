package com.pl.football.backend.service.match.impl;

import com.pl.football.backend.dto.match.MatchQueryDTO;
import com.pl.football.backend.repository.MatchRepository;
import com.pl.football.backend.service.match.MatchQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MatchQueryServiceImpl implements MatchQueryService {
    private final MatchRepository matchRepository;
    @Autowired
    public MatchQueryServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public List<MatchQueryDTO> getAllMatch() {
        return null;
    }

    @Override
    public MatchQueryDTO getMatchById(UUID id) {
        return null;
    }
}
