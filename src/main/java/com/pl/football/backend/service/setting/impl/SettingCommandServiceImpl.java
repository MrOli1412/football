package com.pl.football.backend.service.setting.impl;

import com.pl.football.backend.dto.setting.SettingCreateDTO;
import com.pl.football.backend.dto.setting.SettingQueryDTO;
import com.pl.football.backend.dto.setting.SettingUpdateDTO;
import com.pl.football.backend.repository.SettingRepository;
import com.pl.football.backend.service.setting.SettingCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SettingCommandServiceImpl implements SettingCommandService {

    private final SettingRepository settingRepository;

    @Autowired
    public SettingCommandServiceImpl(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    @Override
    public UUID createSetting(SettingCreateDTO settingCreateDTO) {
        return null;
    }

    @Override
    public SettingQueryDTO updateSetting(UUID id, SettingUpdateDTO settingUpdateDTO) {
        return null;
    }
}
