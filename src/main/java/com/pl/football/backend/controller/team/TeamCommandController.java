package com.pl.football.backend.controller.team;

import com.pl.football.backend.dto.team.TeamCreateDTO;
import com.pl.football.backend.dto.team.TeamClubDTO;
import com.pl.football.backend.dto.team.TeamUpdateDTO;
import com.pl.football.backend.dto.user.UserPrinciple;
import com.pl.football.backend.service.team.TeamService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/team")
@CrossOrigin(value = "*",maxAge = 6000)
@Log4j2
public class TeamCommandController {
    private final TeamService teamService;

    @Autowired
    public TeamCommandController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<UUID> createTeam(@RequestBody TeamCreateDTO team) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(teamService.createTeam(team, userPrinciple.getClubId()));
    }


    @PutMapping(path = "{id}")
    public ResponseEntity<TeamClubDTO> updateTeam(@PathVariable("id") UUID id, @RequestBody TeamUpdateDTO teamUpdateDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(teamService.updateTeam(id, teamUpdateDTO));
    }
}
