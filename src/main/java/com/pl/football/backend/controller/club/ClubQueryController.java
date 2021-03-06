package com.pl.football.backend.controller.club;

import com.pl.football.backend.dto.club.ClubQueryDTO;
import com.pl.football.backend.service.club.ClubQueryService;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/club")
@Log4j2
public class ClubQueryController {

    private final ClubQueryService clubQueryService;

    @Autowired
    public ClubQueryController(ClubQueryService clubQueryService) {
        this.clubQueryService = clubQueryService;
    }

//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
////    @PreAuthorize(" hasRole('ADMIN')")
//    public ResponseEntity<List<ClubQueryDTO>> getAllUsers() {
//        log.debug("Search all clubs");
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(clubQueryService.getAllClubs());
//    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClubQueryDTO> getClubById(@RequestHeader("Authorization") String auth) {
        log.info(auth);

//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(auth);
    return null;
    }
}
