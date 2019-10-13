package com.pl.football.backend.controller;

import com.pl.football.backend.service.MatchService;
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
@RequestMapping("api/match")
public class MatchController {
    private final MatchService matchService;
    private static Logger logger = LoggerFactory.getLogger(MatchController.class);

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Get All Match");
    }
}
