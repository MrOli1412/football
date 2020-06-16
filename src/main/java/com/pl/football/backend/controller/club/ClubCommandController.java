package com.pl.football.backend.controller.club;

import com.pl.football.backend.dto.club.ClubCreateDTO;
import com.pl.football.backend.dto.club.ClubQueryDTO;
import com.pl.football.backend.dto.club.ClubUpdateDTO;
import com.pl.football.backend.service.club.ClubService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/club")
@Log4j2
public class ClubCommandController {

    private final ClubService clubService;

@Autowired
    public ClubCommandController(ClubService clubService) {
        this.clubService = clubService;
    }

    @PostMapping()
    public ResponseEntity<UUID> createClub(@RequestBody ClubCreateDTO createDTO){

    return ResponseEntity.status(HttpStatus.ACCEPTED).body(clubService.createClub(createDTO));
    }
    @PutMapping(path = "{id}")
    public ResponseEntity<ClubQueryDTO > updateClub(@PathVariable("id")UUID id, @RequestBody ClubUpdateDTO clubUpdateDTO){
        return  ResponseEntity.status(HttpStatus.ACCEPTED).body(clubService.updateClub(id,clubUpdateDTO));
    }
}
