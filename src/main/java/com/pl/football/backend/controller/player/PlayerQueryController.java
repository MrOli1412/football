package com.pl.football.backend.controller.player;

import com.pl.football.backend.dto.player.PlayerQueryDTO;
import com.pl.football.backend.service.player.PlayerQueryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/player")
@Log4j2
public class PlayerQueryController {
    private final PlayerQueryService playerQueryService;
    @Autowired
    public PlayerQueryController(PlayerQueryService playerQueryService) {
        this.playerQueryService = playerQueryService;
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlayerQueryDTO>> getAllPlayers(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(playerQueryService.getAllPlayers());
    }
    @GetMapping(path = "{id}")
    public ResponseEntity<PlayerQueryDTO> getPlayerById(@PathVariable("id") UUID id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(playerQueryService.getPlayerById(id));
    }
}
