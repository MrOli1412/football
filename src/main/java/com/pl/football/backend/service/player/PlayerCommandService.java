package com.pl.football.backend.service.player;

import com.pl.football.backend.dto.player.PlayerCreateDTO;
import com.pl.football.backend.dto.player.PlayerQueryDTO;
import com.pl.football.backend.dto.player.PlayerUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface PlayerCommandService {
    UUID createPlayer(PlayerCreateDTO playerCreateDTO);

    PlayerQueryDTO updatePlayer(UUID id, PlayerUpdateDTO playerUpdateDTO);
}
