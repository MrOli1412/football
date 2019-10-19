package com.pl.football.backend.service.club.impl;

import com.pl.football.backend.dto.club.ClubCreateDTO;
import com.pl.football.backend.dto.club.ClubQueryDTO;
import com.pl.football.backend.dto.club.ClubUpdateDTO;
import com.pl.football.backend.service.club.ClubCommandService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClubCommandServiceImpl implements ClubCommandService {
    @Override
    public UUID createClub(ClubCreateDTO clubCreateDTO) {
        return null;
    }

    @Override
    public ClubQueryDTO updateClub(UUID id, ClubUpdateDTO clubUpdateDTO) {
        return null;
    }
}
