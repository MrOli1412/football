package com.pl.football.backend.controller.dress;

import com.pl.football.backend.dto.dress.DressCreateDTO;
import com.pl.football.backend.dto.dress.DressQueryDTO;
import com.pl.football.backend.dto.dress.DressUpdateDTO;
import com.pl.football.backend.service.dress.DressService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/dress")
@Log4j2
@CrossOrigin(value = "*", maxAge = 6000)


public class DressCommandController {
    private final DressService dressService;

    @Autowired
    public DressCommandController(DressService dressService) {
        this.dressService = dressService;
    }

    @PostMapping(path = "{teamId}/save")
    public ResponseEntity<UUID> createDress(@PathVariable("teamId") UUID teamId, @RequestBody DressCreateDTO dressCreateDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dressService.createDress(teamId, dressCreateDTO));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<DressQueryDTO> updateDress(@PathVariable("id") UUID id, @RequestBody DressUpdateDTO dressUpdateDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dressService.updateDress(id, dressUpdateDTO));
    }

}
