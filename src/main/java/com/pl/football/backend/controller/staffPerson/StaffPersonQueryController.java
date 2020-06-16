package com.pl.football.backend.controller.staffPerson;

import com.pl.football.backend.dto.staffPerson.StaffPersonQueryMatchDTO;
import com.pl.football.backend.service.staffPerson.StaffService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/staffPerson")
@CrossOrigin(value = "*", maxAge = 6000)
public class StaffPersonQueryController {

    private final StaffService staffService;

    public StaffPersonQueryController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping(path = "{teamId}")
    public ResponseEntity<List<StaffPersonQueryMatchDTO>> getPlayerById(@PathVariable("teamId") UUID id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(staffService.getStaffByTeamId(id));
    }

}
