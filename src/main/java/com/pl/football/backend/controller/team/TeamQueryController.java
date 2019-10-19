package com.pl.football.backend.controller.team;

import com.pl.football.backend.dto.team.TeamQueryDTO;
import com.pl.football.backend.service.team.TeamQueryService;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("api/team")
@Log4j2
public class TeamQueryController {
    private final TeamQueryService teamQueryService;

    @Autowired
    public TeamQueryController(TeamQueryService teamQueryService) {
        this.teamQueryService = teamQueryService;
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TeamQueryDTO>> getAllTeams(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(teamQueryService.getAllTeams());
    }
    @GetMapping(path="{id}")
    public ResponseEntity<TeamQueryDTO> getTeamById(@PathVariable("id") UUID id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(teamQueryService.getTeamById(id));
    }
}
