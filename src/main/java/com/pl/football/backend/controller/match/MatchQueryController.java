package com.pl.football.backend.controller.match;

import com.pl.football.backend.dto.match.MatchQueryDTO;
import com.pl.football.backend.dto.match.MatchShortInfoDTO;
import com.pl.football.backend.service.match.MatchReportService;
import com.pl.football.backend.service.match.MatchService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/match")
@Log4j2
@CrossOrigin(value = "*",maxAge = 6000)
public class MatchQueryController {
    private final MatchService matchService;
    private final MatchReportService matchReportService;

    @Autowired
    public MatchQueryController(MatchService matchService, MatchReportService matchReportService) {
        this.matchService = matchService;
        this.matchReportService = matchReportService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MatchQueryDTO>> getAllMatch() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(matchService.getAllMatch());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<List<MatchShortInfoDTO>> getMatchById(@PathVariable("id") UUID id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(matchService.getMatchByTeamId(id));
    }

    @GetMapping(path = "/create/{teamId}")
    public ResponseEntity<MatchQueryDTO> getInfoToFrom(@PathVariable("teamId")UUID id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(matchService.getInfoToFrom(id));
    }




}
