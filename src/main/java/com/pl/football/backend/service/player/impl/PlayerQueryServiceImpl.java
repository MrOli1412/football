package com.pl.football.backend.service.player.impl;

import com.pl.football.backend.dto.player.PlayerQueryDTO;
import com.pl.football.backend.repository.PlayerRepository;
import com.pl.football.backend.service.player.PlayerQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlayerQueryServiceImpl implements PlayerQueryService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerQueryServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public List<PlayerQueryDTO> getAllPlayers() {
        return null;
    }

    @Override
    public PlayerQueryDTO getPlayerById(UUID id) {
        return null;
    }
}
