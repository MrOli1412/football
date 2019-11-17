package com.pl.football.backend.controller.player;

import com.pl.football.backend.dto.player.PlayerCreateDTO;
import com.pl.football.backend.dto.player.PlayerFullDataDTO;
import com.pl.football.backend.dto.player.PlayerUpdateDTO;
import com.pl.football.backend.service.player.PlayerCommandService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/player")
@Log4j2
public class PlayerCommandController {

    private final PlayerCommandService playerCommandService;

    @Autowired
    public PlayerCommandController(PlayerCommandService playerCommandService) {
        this.playerCommandService = playerCommandService;
    }

    @PostMapping()
    public ResponseEntity<UUID> savePlayer(@RequestBody PlayerCreateDTO playerCreateDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(playerCommandService.createPlayer(playerCreateDTO));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<PlayerFullDataDTO> updatePlayer(@PathVariable("id") UUID id, @RequestBody PlayerUpdateDTO playerUpdateDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(playerCommandService.updatePlayer(id, playerUpdateDTO));
    }

    @PostMapping(path = "/upload")
    public ResponseEntity<List<PlayerFullDataDTO>> uploadFile(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(playerCommandService.convertFile(file));
    }

    @PostMapping("{teamId}/file")
    public ResponseEntity<Map<String, List<PlayerFullDataDTO>>>
    savePlayersFromFile(@PathVariable("teamId") UUID id,
                        @RequestBody List<PlayerFullDataDTO> playersFromFile) {
        Map<String, List<PlayerFullDataDTO>> stringListMap = this.playerCommandService.savePlayersFromFile(id, playersFromFile);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(stringListMap);
    }
}
