package com.pl.football.backend.controller.dress;

import com.pl.football.backend.dto.dress.DressQueryDTO;
import com.pl.football.backend.service.dress.DressQueryService;
import lombok.extern.log4j.Log4j2;
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
@RequestMapping("api/dress")
@Log4j2
public class DressQueryController {

    private final DressQueryService dressQueryService;
@Autowired
    public DressQueryController(DressQueryService dressQueryService) {
        this.dressQueryService = dressQueryService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DressQueryDTO>> getAllDresses() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dressQueryService.getAllDresses());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<DressQueryDTO> getDressById(@PathVariable("id") UUID id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dressQueryService.getDressById(id));
    }

}
