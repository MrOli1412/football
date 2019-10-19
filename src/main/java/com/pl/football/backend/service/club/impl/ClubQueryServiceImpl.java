package com.pl.football.backend.service.club.impl;

import com.pl.football.backend.dto.club.ClubQueryDTO;
import com.pl.football.backend.repository.ClubRepository;
import com.pl.football.backend.service.club.ClubQueryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClubQueryServiceImpl implements ClubQueryService {

    private final ClubRepository clubRepository;

    public ClubQueryServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public ClubQueryDTO getClubById(UUID id) {
        return null;
    }

    @Override
    public List<ClubQueryDTO> getAllClubs() {
        return null;
    }
}
