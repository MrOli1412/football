package com.pl.football.backend.controller.player;

import com.pl.football.backend.dto.player.PlayerFullDataDTO;
import com.pl.football.backend.dto.player.PlayerShortDTO;
import com.pl.football.backend.service.player.PlayerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/players")
@CrossOrigin(value = "*",maxAge = 6000)
@Log4j2
public class PlayerQueryController {
    private final PlayerService playerService;
    @Autowired
    public PlayerQueryController(PlayerService playerService) {
        this.playerService = playerService;
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlayerFullDataDTO>> getAllPlayers(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(playerService.getAllPlayers());
    }
    @GetMapping(path = "{teamId}")
    public ResponseEntity<List<PlayerShortDTO>> getPlayerById(@PathVariable("teamId") UUID id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(playerService.getPlayerByTeamId(id));
    }

}
