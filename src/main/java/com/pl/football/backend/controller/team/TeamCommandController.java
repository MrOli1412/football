package com.pl.football.backend.controller.team;

import com.pl.football.backend.dto.team.TeamCreateDTO;
import com.pl.football.backend.dto.team.TeamQueryDTO;
import com.pl.football.backend.dto.team.TeamUpdateDTO;
import com.pl.football.backend.service.team.TeamCommandService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/team")
@Log4j2
public class TeamCommandController {
private final TeamCommandService teamCommandService;
    @Autowired
    public TeamCommandController(TeamCommandService teamCommandService) {
        this.teamCommandService = teamCommandService;
    }

    @PostMapping()
    public ResponseEntity<UUID> createTeam(@RequestBody TeamCreateDTO teamCreateDTO){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(teamCommandService.createTeam(teamCreateDTO));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<TeamQueryDTO> updateTeam(@PathVariable("id")UUID id,@RequestBody TeamUpdateDTO teamUpdateDTO){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(teamCommandService.updateTeam(id,teamUpdateDTO));
    }
}
