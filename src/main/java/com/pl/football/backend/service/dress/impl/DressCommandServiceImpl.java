package com.pl.football.backend.service.dress.impl;

import com.pl.football.backend.dto.dress.DressCreateDTO;
import com.pl.football.backend.dto.dress.DressQueryDTO;
import com.pl.football.backend.dto.dress.DressUpdateDTO;
import com.pl.football.backend.dto.team.TeamQueryDTO;
import com.pl.football.backend.model.Dress;
import com.pl.football.backend.model.Team;
import com.pl.football.backend.repository.DressRepository;
import com.pl.football.backend.repository.TeamRepository;
import com.pl.football.backend.service.dress.DressCommandService;
import com.pl.football.backend.service.team.TeamQueryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class DressCommandServiceImpl implements DressCommandService {

    private final DressRepository dressRepository;
    private final TeamQueryService teamQueryService;
    private final TeamRepository teamRepository;

    @Autowired
    public DressCommandServiceImpl(DressRepository dressRepository, TeamQueryService teamQueryService, TeamRepository teamRepository) {
        this.dressRepository = dressRepository;
        this.teamQueryService = teamQueryService;
        this.teamRepository = teamRepository;
    }

    @Override
    public UUID createDress(UUID teamId, DressCreateDTO dressCreateDTO) {
        ModelMapper modelMapper = new ModelMapper();
        TeamQueryDTO teamById = teamQueryService.getTeamById(teamId);
        Team team = modelMapper.map(teamById, Team.class);
        Dress dress = modelMapper.map(dressCreateDTO, Dress.class);
        dress.setTeam(team);
        dressRepository.saveAndFlush(dress);
        Set<Dress> dresses = team.getDresses() == null ? new HashSet<>() : team.getDresses();
        dresses.add(dress);
        team.setDresses(dresses);
        teamRepository.save(team);
        return  dress.getId();
    }

    @Override
    public DressQueryDTO updateDress(UUID id, DressUpdateDTO dressUpdateDTO) {
        Dress dress = dressRepository.getOne(id);
        dress.setColor(dressUpdateDTO.getColor());
        dress.setAllowedNumbers(dressUpdateDTO.getAllowedNumbers());
        Dress result = dressRepository.save(dress);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(result, DressQueryDTO.class);

    }
}
