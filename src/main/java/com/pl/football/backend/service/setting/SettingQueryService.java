package com.pl.football.backend.service.setting;

import com.pl.football.backend.dto.setting.SettingQueryDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface SettingQueryService {
    List<SettingQueryDTO> getAllSettings();

    SettingQueryDTO getSettingById(UUID id);
}
