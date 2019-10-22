package com.pl.football.backend.service.club.impl;

import com.pl.football.backend.dto.club.ClubCreateDTO;
import com.pl.football.backend.dto.club.ClubQueryDTO;
import com.pl.football.backend.dto.club.ClubUpdateDTO;
import com.pl.football.backend.model.Club;
import com.pl.football.backend.model.User;
import com.pl.football.backend.repository.ClubRepository;
import com.pl.football.backend.service.club.ClubCommandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClubCommandServiceImpl implements ClubCommandService {

    private final ClubRepository clubRepository;

    public ClubCommandServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public UUID createClub(ClubCreateDTO clubCreateDTO) {

        ModelMapper modelMapper = new ModelMapper();
        Club club = modelMapper.map(clubCreateDTO, Club.class);
        club = clubRepository.save(club);
        return club.getId();
    }

    @Override
    public ClubQueryDTO updateClub(UUID id, ClubUpdateDTO clubUpdateDTO) {
        return null;
    }
}
