package com.pl.football.backend.controller.match;

import com.pl.football.backend.dto.match.MatchCreateDTO;
import com.pl.football.backend.dto.match.MatchQueryDTO;
import com.pl.football.backend.dto.match.MatchUpdateDTO;
import com.pl.football.backend.service.match.MatchCommandService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/match")
@Log4j2
public class MatchCommandController {
    private final MatchCommandService matchCommandService;
@Autowired
    public MatchCommandController(MatchCommandService matchCommandService) {
        this.matchCommandService = matchCommandService;
    }

    @PostMapping()
    public ResponseEntity<UUID> createMatch(@RequestBody MatchCreateDTO match){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(matchCommandService.createMatch(match));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<MatchQueryDTO> updateMatch(@PathVariable("id")UUID id, MatchUpdateDTO matchUpdateDTO){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(matchCommandService.updateMatch(id,matchUpdateDTO));
    }
}
