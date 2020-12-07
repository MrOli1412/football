package com.pl.football.backend.controller.staffPerson;

import com.pl.football.backend.dto.staffPerson.StaffPersonCommandDTO;
import com.pl.football.backend.dto.staffPerson.StaffPersonQueryDTO;
import com.pl.football.backend.service.staffPerson.StaffService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/staffPerson")
@CrossOrigin(value = "*", maxAge = 6000)
public class StaffPersonCommandController {
    private final StaffService staffService;

    public StaffPersonCommandController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping(path = "{teamId}/save")
    public ResponseEntity<StaffPersonQueryDTO> savePlayer(@PathVariable("teamId")UUID teamId,@RequestBody StaffPersonCommandDTO staffPersonCommandDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(staffService.savePerson(teamId,staffPersonCommandDTO));
    }

    @PostMapping(path = "{teamId}/update")
    public ResponseEntity<StaffPersonQueryDTO> savePlayer(@PathVariable("teamId")UUID teamId,@PathVariable("id") UUID id, @RequestBody StaffPersonCommandDTO staffPersonCommandDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(staffService.updatePerson(id, staffPersonCommandDTO));
    }
}
