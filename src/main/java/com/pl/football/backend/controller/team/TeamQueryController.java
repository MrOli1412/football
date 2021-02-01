package com.pl.football.backend.controller.team;

import com.pl.football.backend.dto.team.TeamClubDTO;
import com.pl.football.backend.dto.team.TeamShortDTO;
import com.pl.football.backend.dto.user.UserPrinciple;
import com.pl.football.backend.service.team.TeamService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/team")
@CrossOrigin(value = "*",maxAge = 6000)
@Log4j2
public class TeamQueryController {
    private final TeamService teamService;

    @Autowired
    public TeamQueryController(TeamService teamService) {
        this.teamService = teamService;
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TeamClubDTO>> getAllTeams(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(teamService.getTeamsForClub(userPrinciple.getClubId()));
    }
    @GetMapping(path="{id}")
    public ResponseEntity<TeamShortDTO> getTeamById(@PathVariable("id") UUID id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(teamService.getShortInfo(id));
    }
    @GetMapping(path = "/generate")
    public ResponseEntity<?> generateTeamsFromPZPN() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.OK).body(teamService.importTeams(userPrinciple.getPzpnTemaId(),userPrinciple.getClubId()));
    }
}
