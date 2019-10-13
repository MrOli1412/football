package com.pl.football.backend.controller;

import com.pl.football.backend.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/player")
public class PlayerController {
    private final PlayerService playerService;
    private static Logger logger = LoggerFactory.getLogger(PlayerController.class);

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllPlayers(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Get All Players");
    }
}
