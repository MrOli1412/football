package com.pl.football.backend.controller.setting;

import com.pl.football.backend.dto.setting.SettingCreateDTO;
import com.pl.football.backend.dto.setting.SettingQueryDTO;
import com.pl.football.backend.dto.setting.SettingUpdateDTO;
import com.pl.football.backend.service.setting.SettingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/setting")
@Log4j2
public class SettingCommandController {
    private final SettingService settingService;

    @Autowired
    public SettingCommandController(SettingService settingService) {
        this.settingService = settingService;
    }

    @PostMapping()
    public ResponseEntity<UUID> saveSetting(@RequestBody SettingCreateDTO settingCreateDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(settingService.createSetting(settingCreateDTO));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<SettingQueryDTO> updateSetting(@PathVariable("id") UUID id, @RequestBody SettingUpdateDTO settingUpdateDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(settingService.updateSetting(id, settingUpdateDTO));
    }
}
