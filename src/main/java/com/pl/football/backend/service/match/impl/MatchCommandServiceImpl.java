package com.pl.football.backend.service.match.impl;

import com.pl.football.backend.dto.match.MatchCreateDTO;
import com.pl.football.backend.dto.match.MatchQueryDTO;
import com.pl.football.backend.dto.match.MatchUpdateDTO;
import com.pl.football.backend.model.Dress;
import com.pl.football.backend.model.Match;
import com.pl.football.backend.model.Player;
import com.pl.football.backend.model.Team;
import com.pl.football.backend.repository.DressRepository;
import com.pl.football.backend.repository.MatchRepository;
import com.pl.football.backend.repository.PlayerRepository;
import com.pl.football.backend.repository.TeamRepository;
import com.pl.football.backend.service.match.MatchCommandService;
import com.pl.football.backend.service.player.PlayerQueryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class MatchCommandServiceImpl implements MatchCommandService {

    private final MatchRepository matchRepository;
    private final PlayerQueryService playerQueryService;
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final DressRepository dressRepository;

    @Autowired
    public MatchCommandServiceImpl(MatchRepository matchRepository, PlayerQueryService playerQueryService, PlayerRepository playerRepository, TeamRepository teamRepository, DressRepository dressRepository) {
        this.matchRepository = matchRepository;
        this.playerQueryService = playerQueryService;
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.dressRepository = dressRepository;
    }

    @Override
    public UUID createMatch(UUID teamId, MatchCreateDTO match) {
        ModelMapper modelMapper = new ModelMapper();
        Set<Player> playerToSave = new HashSet<>();
        Team team = teamRepository.getOne(teamId);
        Dress dress = dressRepository.getOne(match.getDress());
        match.getPlayers().forEach(playerDto -> {
            playerToSave.add(playerRepository.getPlayerByEvidentialNumber(playerDto.getEvidentialNumber()));
        });

        Match result = modelMapper.map(match, Match.class);
        result.setPlayers(playerToSave);
        result.setTeam(team);
        result.setDress(dress);
        matchRepository.saveAndFlush(result);
        return result.getId();
    }

    @Override
    public MatchQueryDTO updateMatch(UUID id, MatchUpdateDTO matchUpdateDTO) {
        return null;
    }
}
