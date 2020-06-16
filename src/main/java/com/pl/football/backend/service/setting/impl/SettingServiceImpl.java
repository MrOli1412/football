package com.pl.football.backend.service.setting.impl;

import com.pl.football.backend.dto.setting.SettingCreateDTO;
import com.pl.football.backend.dto.setting.SettingQueryDTO;
import com.pl.football.backend.dto.setting.SettingUpdateDTO;
import com.pl.football.backend.service.setting.SettingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SettingServiceImpl implements SettingService {
    @Override
    public UUID createSetting(SettingCreateDTO settingCreateDTO) {
        return null;
    }

    @Override
    public SettingQueryDTO updateSetting(UUID id, SettingUpdateDTO settingUpdateDTO) {
        return null;
    }

    @Override
    public List<SettingQueryDTO> getAllSettings() {
        return null;
    }

    @Override
    public SettingQueryDTO getSettingById(UUID id) {
        return null;
    }
}
