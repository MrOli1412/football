package com.pl.football.backend.service.setting.impl;

import com.pl.football.backend.dto.setting.SettingQueryDTO;
import com.pl.football.backend.repository.SettingRepository;
import com.pl.football.backend.service.setting.SettingQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SettingQueryServiceImpl implements SettingQueryService {
    private final SettingRepository settingRepository;
    @Autowired
    public SettingQueryServiceImpl(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
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
