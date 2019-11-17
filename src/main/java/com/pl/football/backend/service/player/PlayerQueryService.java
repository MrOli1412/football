package com.pl.football.backend.service.player;

import com.pl.football.backend.dto.player.PlayerFullDataDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface PlayerQueryService {
    List<PlayerFullDataDTO> getAllPlayers();

    PlayerFullDataDTO getPlayerById(UUID id);
}
