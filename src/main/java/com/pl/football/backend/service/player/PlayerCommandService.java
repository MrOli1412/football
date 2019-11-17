package com.pl.football.backend.service.player;

import com.pl.football.backend.dto.player.PlayerCreateDTO;
import com.pl.football.backend.dto.player.PlayerFullDataDTO;
import com.pl.football.backend.dto.player.PlayerUpdateDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public interface PlayerCommandService {
    UUID createPlayer(PlayerCreateDTO playerCreateDTO);

    PlayerFullDataDTO updatePlayer(UUID id, PlayerUpdateDTO playerUpdateDTO);

    List<PlayerFullDataDTO> convertFile(MultipartFile multipartFile);
    Map<String, List<PlayerFullDataDTO>> savePlayersFromFile(UUID teamId, List<PlayerFullDataDTO> playersFromFile);
}
