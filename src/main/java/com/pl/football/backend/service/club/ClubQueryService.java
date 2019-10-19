package com.pl.football.backend.service.club;

import com.pl.football.backend.dto.club.ClubQueryDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ClubQueryService {
    ClubQueryDTO getClubById(UUID id);
    List<ClubQueryDTO> getAllClubs();
}
