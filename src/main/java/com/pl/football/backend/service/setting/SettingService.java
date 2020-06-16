package com.pl.football.backend.service.setting;

import com.pl.football.backend.dto.setting.SettingCreateDTO;
import com.pl.football.backend.dto.setting.SettingQueryDTO;
import com.pl.football.backend.dto.setting.SettingUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface SettingService {
    UUID createSetting(SettingCreateDTO settingCreateDTO);

    SettingQueryDTO updateSetting(UUID id, SettingUpdateDTO settingUpdateDTO);
    List<SettingQueryDTO> getAllSettings();

    SettingQueryDTO getSettingById(UUID id);
}
