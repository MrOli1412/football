package com.pl.football.backend.controller.player;

import com.pl.football.backend.dto.player.PlayerFullDataDTO;
import com.pl.football.backend.dto.player.PlayerUpdateDTO;
import com.pl.football.backend.service.player.PlayerService;
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
@RequestMapping("/api/players")
@CrossOrigin(value = "*", maxAge = 6000)

@Log4j2
public class PlayerCommandController {

    private final PlayerService playerService;

    @Autowired
    public PlayerCommandController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping(path = "{teamId}")
    public ResponseEntity<UUID> savePlayer(@PathVariable("teamId") UUID teamId, @RequestBody PlayerFullDataDTO playerCreateDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(playerService.createPlayer(teamId, playerCreateDTO));
    }

    @PutMapping(path = "{teamId}/{id}")
    public ResponseEntity<PlayerFullDataDTO> updatePlayer(@PathVariable("teamId") UUID teamId, @PathVariable("id") UUID id, @RequestBody PlayerUpdateDTO playerUpdateDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(playerService.updatePlayer(id, playerUpdateDTO));
    }

    @PostMapping(path = "{teamId}/upload")
    public ResponseEntity<List<PlayerFullDataDTO>> uploadFile(@PathVariable("teamId") UUID id, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(playerService.convertFile(file));
    }

    @PostMapping("{teamId}/file")
    public ResponseEntity<Map<String, List<PlayerFullDataDTO>>>
    savePlayersFromFile(@PathVariable("teamId") UUID id,
                        @RequestBody List<PlayerFullDataDTO> playersFromFile) {
        Map<String, List<PlayerFullDataDTO>> stringListMap = this.playerService.savePlayersFromFile(id, playersFromFile);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(stringListMap);
    }


}
