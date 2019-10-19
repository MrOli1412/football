package com.pl.football.backend.service.player.impl;

import com.pl.football.backend.dto.player.PlayerCreateDTO;
import com.pl.football.backend.dto.player.PlayerQueryDTO;
import com.pl.football.backend.dto.player.PlayerUpdateDTO;
import com.pl.football.backend.repository.PlayerRepository;
import com.pl.football.backend.service.player.PlayerCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PlayerCommandServiceImpl implements PlayerCommandService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerCommandServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public UUID createPlayer(PlayerCreateDTO playerCreateDTO) {
        return null;
    }

    @Override
    public PlayerQueryDTO updatePlayer(UUID id, PlayerUpdateDTO playerUpdateDTO) {
        return null;
    }
}
