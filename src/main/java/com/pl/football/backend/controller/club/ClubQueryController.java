package com.pl.football.backend.controller.club;

import com.pl.football.backend.dto.club.ClubQueryDTO;
import com.pl.football.backend.dto.user.UserPrinciple;
import com.pl.football.backend.exception.FootballException;
import com.pl.football.backend.service.club.ClubService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/club")
@Log4j2
@CrossOrigin(value = "*", maxAge = 6000)


public class ClubQueryController {

    private final ClubService clubService;


    @Autowired
    public ClubQueryController(ClubService clubService) {
        this.clubService = clubService;
    }

//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
////    @PreAuthorize(" hasRole('ADMIN')")
//    public ResponseEntity<List<ClubQueryDTO>> getAllUsers() {
//        log.debug("Search all clubs");
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(clubQueryService.getAllClubs());
//    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClubQueryDTO> getClubById() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
            return ResponseEntity.status(200).body(this.clubService.getClubById(userPrinciple.getClubId()));
        } catch (Exception ex) {
            throw new FootballException(HttpStatus.UNAUTHORIZED,"Unuthorized");
        }

    }
}
