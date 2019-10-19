package com.pl.football.backend.service.player;

import com.pl.football.backend.dto.player.PlayerQueryDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface PlayerQueryService {
    List<PlayerQueryDTO> getAllPlayers();

    PlayerQueryDTO getPlayerById(UUID id);
}
