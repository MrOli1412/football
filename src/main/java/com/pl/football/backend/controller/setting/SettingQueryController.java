package com.pl.football.backend.controller.setting;

import com.pl.football.backend.dto.setting.SettingQueryDTO;
import com.pl.football.backend.service.setting.SettingService;
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
@RequestMapping("api/setting")
@Log4j2
public class SettingQueryController {
    private final SettingService settingService;
@Autowired
    public SettingQueryController(SettingService settingService) {
        this.settingService = settingService;
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SettingQueryDTO>> getAllSettings(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(settingService.getAllSettings());
    }
    @GetMapping(path = "{id}")
    public ResponseEntity<SettingQueryDTO> getSettingById(@PathVariable("id") UUID id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(settingService.getSettingById(id));
    }
}
