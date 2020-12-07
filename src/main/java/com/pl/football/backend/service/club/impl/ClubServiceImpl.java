package com.pl.football.backend.service.club.impl;

import com.pl.football.backend.dto.club.ClubCreateDTO;
import com.pl.football.backend.dto.club.ClubQueryDTO;
import com.pl.football.backend.dto.club.ClubUpdateDTO;
import com.pl.football.backend.exception.FootballException;
import com.pl.football.backend.model.Club;
import com.pl.football.backend.repository.ClubRepository;
import com.pl.football.backend.service.club.ClubService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public ClubQueryDTO getClubById(UUID id) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(clubRepository.findById(id).orElseThrow(() -> new FootballException("Club does not exist")), ClubQueryDTO.class);
    }



    @Override
    public List<ClubQueryDTO> getAllClubs() {
        //TODO
        return null;
    }

    @Transactional
    @Override
    public UUID createClub(ClubCreateDTO clubCreateDTO) {
        if(clubRepository.existsByClubName(clubCreateDTO.getClubName())){
            throw new FootballException("Existing club");
        }else{
            ModelMapper modelMapper = new ModelMapper();
            Club club = modelMapper.map(clubCreateDTO, Club.class);
            club = clubRepository.save(club);
            return club.getId();
        }
    }

    @Override
    public ClubQueryDTO updateClub(UUID id, ClubUpdateDTO clubUpdateDTO) {
        Club club  = clubRepository.getById(id).orElseThrow(() -> new FootballException("Club does not exist"));
        ModelMapper modelMapper = new ModelMapper();
        club.setClubName(clubUpdateDTO.getClubName());
        club = clubRepository.saveAndFlush(club);
        return modelMapper.map(club, ClubQueryDTO.class);

    }
}
