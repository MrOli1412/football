package com.pl.football.backend.service.club.impl;

import com.pl.football.backend.dto.club.ClubQueryDTO;
import com.pl.football.backend.model.Club;
import com.pl.football.backend.repository.ClubRepository;
import com.pl.football.backend.service.club.ClubQueryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClubQueryServiceImpl implements ClubQueryService {

    private final ClubRepository clubRepository;

    public ClubQueryServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public ClubQueryDTO getClubById(UUID id) {
        Optional<Club> byId = clubRepository.findById(id);
        if (byId.isPresent()) {
            ModelMapper modelMapper = new ModelMapper();
            ClubQueryDTO club = modelMapper.map(byId.get(), ClubQueryDTO.class);
            return club;
        }
        return null;
    }

    @Override
    public List<ClubQueryDTO> getAllClubs() {
        List<ClubQueryDTO> result = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        List<Club> all = clubRepository.findAll();
        all.forEach(club -> {
            result.add(modelMapper.map(club, ClubQueryDTO.class));
        });
        return result;
    }
}
