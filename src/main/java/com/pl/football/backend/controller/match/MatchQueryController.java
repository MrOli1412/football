package com.pl.football.backend.controller.match;

import com.pl.football.backend.dto.match.MatchQueryDTO;
import com.pl.football.backend.service.match.MatchQueryService;
import com.pl.football.backend.service.match.MatchReportService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/match")
@Log4j2
public class MatchQueryController {
    private final MatchQueryService matchQueryService;
    private final MatchReportService matchReportService;

    @Autowired
    public MatchQueryController(MatchQueryService matchQueryService, MatchReportService matchReportService) {
        this.matchQueryService = matchQueryService;
        this.matchReportService = matchReportService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MatchQueryDTO>> getAllMatch() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(matchQueryService.getAllMatch());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<MatchQueryDTO> getMatchById(@PathVariable("id") UUID id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(matchQueryService.getMatchById(id));
    }

    @GetMapping(path = "/generate/{id}")
    public ResponseEntity<Object> generateReport(@PathVariable("id") UUID matchId) throws IOException {
        matchReportService.convertFile(matchId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("");
    }

}
