package com.pl.football.backend.controller.pzpn;

import com.pl.football.backend.model.pzpn.League;
import com.pl.football.backend.model.pzpn.State;
import com.pl.football.backend.service.pzpn.PzpnService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/pzpn")
@Log4j2
@CrossOrigin(value = "*", maxAge = 6000)
public class PZPNQueryController {

    private final PzpnService pzpnService;

    @Autowired
    public PZPNQueryController(PzpnService pzpnService) {
        this.pzpnService = pzpnService;
    }

    @GetMapping(path = "/states")
    public ResponseEntity<List<State>> getAllStates() {
        return ResponseEntity.status(200).body(pzpnService.getStates());
    }

    @GetMapping(path = "/leagues")
    public ResponseEntity<?> getLeaguesByStateId(@RequestParam("id") Integer id) {
        try {
            return ResponseEntity.status(200).body(pzpnService.getLeagueByStateId(id));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(path = "/teams")
    public ResponseEntity<?> getTeamsByLeagueId(@RequestParam("id") Integer id) {
        try {
            return ResponseEntity.status(200).body(pzpnService.getTeamsByLeagueId(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


}
