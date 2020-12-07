package com.pl.football.backend.controller.dress;

import com.pl.football.backend.dto.dress.DressQueryDTO;
import com.pl.football.backend.service.dress.DressService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/dress")
@Log4j2
@CrossOrigin(value = "*", maxAge = 6000)


public class DressQueryController {

    private final DressService dressService;
@Autowired
    public DressQueryController(DressService dressService) {
        this.dressService = dressService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DressQueryDTO>> getAllDresses() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dressService.getAllDresses());
    }


    @GetMapping(path = "{teamId}")
    public ResponseEntity<List<DressQueryDTO>> getDressById(@PathVariable("teamId") UUID id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dressService.getDressForTeam(id));
    }

}
