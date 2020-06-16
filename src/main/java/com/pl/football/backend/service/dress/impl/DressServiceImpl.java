package com.pl.football.backend.service.dress.impl;

import com.pl.football.backend.dto.dress.DressCreateDTO;
import com.pl.football.backend.dto.dress.DressQueryDTO;
import com.pl.football.backend.dto.dress.DressUpdateDTO;
import com.pl.football.backend.dto.team.TeamClubDTO;
import com.pl.football.backend.exception.FootballException;
import com.pl.football.backend.model.Dress;
import com.pl.football.backend.model.Team;
import com.pl.football.backend.repository.DressRepository;
import com.pl.football.backend.service.dress.DressService;
import com.pl.football.backend.service.team.TeamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DressServiceImpl implements DressService {

    private final DressRepository dressRepository;
    private final TeamService teamService;


    @Autowired
    public DressServiceImpl(DressRepository dressRepository, TeamService teamService) {
        this.dressRepository = dressRepository;
        this.teamService = teamService;

    }


    @Override
    public UUID createDress(UUID teamId, DressCreateDTO dressCreateDTO) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            TeamClubDTO teamById = teamService.getTeamById(teamId);
            Team team = modelMapper.map(teamById, Team.class);
            Dress dress = modelMapper.map(dressCreateDTO, Dress.class);
            dress.setTeam(team);
            return dressRepository.saveAndFlush(dress).getId();
        } catch (Exception ex) {
            throw new FootballException(HttpStatus.BAD_REQUEST, "Error creating dress" + ex.getMessage());
        }
    }

    @Override
    public DressQueryDTO updateDress(UUID id, DressUpdateDTO dressUpdateDTO) {
        try {

            Dress dress = dressRepository.findById(id).orElseThrow(() -> new FootballException("Dress does not exist"));
            dress.setColor(dressUpdateDTO.getColor());
            dress.setAllowedNumbers(dressUpdateDTO.getAllowedNumbers());
            Dress result = dressRepository.save(dress);
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(result, DressQueryDTO.class);
        } catch (Exception ex) {
            throw new FootballException(HttpStatus.BAD_REQUEST, "Error updating dress" + ex.getMessage());
        }

    }

    @Override
    public List<DressQueryDTO> getAllDresses() {
        return null;

    }

    @Override
    public DressQueryDTO getDressById(UUID id) {

            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(dressRepository.findById(id).orElseThrow(()-> new FootballException("Dress does not exist")), DressQueryDTO.class);

    }

    @Override
    public Integer countDressFromTeam(UUID teamId) {

            return dressRepository.countByTeam_Id(teamId).orElse(0);

    }

    @Override
    public List<DressQueryDTO> getDressForTeam(UUID teamId) {
        try {
            List<DressQueryDTO> result = new ArrayList<>();
            Optional<List<Dress>> dresses = dressRepository.getByTeam_Id(teamId);
            dresses.ifPresent(dressList -> {
                ModelMapper mapper = new ModelMapper();
                dressList.forEach(dress -> {
                    result.add(mapper.map(dress, DressQueryDTO.class));
                });
            });
            return result;
        } catch (Exception ex) {
            throw new FootballException(HttpStatus.BAD_REQUEST, "Error in get Dress for Team" + ex.getMessage());
        }

    }
}
