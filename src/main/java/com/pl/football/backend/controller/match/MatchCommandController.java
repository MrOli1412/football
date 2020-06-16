package com.pl.football.backend.controller.match;

import com.pl.football.backend.dto.match.MatchQueryDTO;
import com.pl.football.backend.dto.match.MatchReportDTO;
import com.pl.football.backend.dto.match.MatchUpdateDTO;
import com.pl.football.backend.service.match.MatchReportService;
import com.pl.football.backend.service.match.MatchService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/match")
@Log4j2
@CrossOrigin(value = "*", maxAge = 6000)

public class MatchCommandController {
    private final MatchService matchService;
    private final MatchReportService matchReportService;

    @Autowired
    public MatchCommandController(MatchService matchService, MatchReportService matchReportService) {
        this.matchService = matchService;
        this.matchReportService = matchReportService;
    }

    @PostMapping(path = "/save/{teamId}")
    public ResponseEntity<UUID> createMatch(@PathVariable("teamId") UUID teamId, @RequestBody MatchReportDTO match) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(matchService.createMatch(teamId, match));
    }

    @PostMapping(path = "/generateReport/{teamId}")
    public ResponseEntity<byte[]> generateReport(@PathVariable("teamId") UUID teamId, @RequestBody MatchReportDTO match) throws IOException {
//        matchCommandService.createMatch(teamId, match);
        Map<String, Object> responseMap = matchReportService.convertFile(match);
        ByteArrayOutputStream file = (ByteArrayOutputStream) responseMap.get("file");
        final HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + responseMap.get("fileName"));
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .headers(headers)
                .body(file.toByteArray());
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<MatchQueryDTO> updateMatch(@PathVariable("id") UUID id, MatchUpdateDTO matchUpdateDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(matchService.updateMatch(id, matchUpdateDTO));
    }
}
