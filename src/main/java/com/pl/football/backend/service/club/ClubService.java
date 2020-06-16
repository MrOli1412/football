package com.pl.football.backend.service.club;

import com.pl.football.backend.dto.club.ClubCreateDTO;
import com.pl.football.backend.dto.club.ClubQueryDTO;
import com.pl.football.backend.dto.club.ClubUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ClubService {
    ClubQueryDTO getClubById(UUID id);
    List<ClubQueryDTO> getAllClubs();
    UUID createClub(ClubCreateDTO clubCreateDTO);
    ClubQueryDTO updateClub(UUID id, ClubUpdateDTO clubUpdateDTO);

}
