package com.pl.football.backend.controller;

import com.pl.football.backend.service.SettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/setting")
public class SettingController {
    private final SettingService settingService;
    private static Logger logger = LoggerFactory.getLogger(SettingController.class);

    public SettingController(SettingService settingService) {
        this.settingService = settingService;
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllSettings(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Get All Settings");
    }
}
