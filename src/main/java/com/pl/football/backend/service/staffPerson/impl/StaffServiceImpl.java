package com.pl.football.backend.service.staffPerson.impl;

import com.pl.football.backend.dto.staffPerson.StaffPersonCommandDTO;
import com.pl.football.backend.dto.staffPerson.StaffPersonQueryDTO;
import com.pl.football.backend.dto.staffPerson.StaffPersonQueryMatchDTO;
import com.pl.football.backend.exception.FootballException;
import com.pl.football.backend.model.Player;
import com.pl.football.backend.model.StaffPerson;
import com.pl.football.backend.model.Team;
import com.pl.football.backend.repository.StaffPersonRepository;
import com.pl.football.backend.repository.TeamRepository;
import com.pl.football.backend.service.staffPerson.StaffService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class StaffServiceImpl implements StaffService {
    private final StaffPersonRepository staffPersonRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public StaffServiceImpl(StaffPersonRepository staffPersonRepository, TeamRepository teamRepository) {
        this.staffPersonRepository = staffPersonRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public StaffPerson getById(UUID id) {
        try {
            return staffPersonRepository.getOne(id);
        } catch (Exception ex) {
            throw new FootballException(HttpStatus.BAD_REQUEST, "Error ing geting Staff by id" + ex.getMessage());
        }
    }

    @Override
    public List<StaffPersonQueryMatchDTO> getStaffByTeamId(UUID id) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            List<StaffPersonQueryMatchDTO> result = new ArrayList<>();
            List<StaffPerson> playersSet = staffPersonRepository.getByTeamId(id).orElse(new ArrayList<>());

            playersSet.forEach(staffPerson -> {
                result.add(modelMapper.map(staffPerson, StaffPersonQueryMatchDTO.class));

            });
            return result;
        } catch (Exception ex) {
            throw new FootballException(HttpStatus.BAD_REQUEST, "Error in getting staff by team id" + ex.getMessage());
        }
    }

    @Override
    public StaffPersonQueryDTO savePerson(UUID teamId, StaffPersonCommandDTO staffPerson) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            StaffPerson personToSave = modelMapper.map(staffPerson, StaffPerson.class);
            Team team = teamRepository.getById(teamId).orElseThrow(() -> new FootballException("Team does not exist"));
            personToSave.setTeam(Collections.singleton(team));
            personToSave = staffPersonRepository.saveAndFlush(personToSave);
            if (team.getStaffPeople().isEmpty()) {
                team.setStaffPeople(Collections.singleton(personToSave));
            } else {
                team.getStaffPeople().add(personToSave);
            }
            teamRepository.saveAndFlush(team);
            return modelMapper.map(personToSave, StaffPersonQueryDTO.class);
        } catch (Exception ex) {
            throw new FootballException(HttpStatus.BAD_REQUEST, "Error in saving staff person" + ex.getMessage());
        }

    }

    @Override
    public StaffPersonQueryDTO updatePerson(UUID id, StaffPersonCommandDTO staffPerson) {
        try {

            ModelMapper modelMapper = new ModelMapper();
            staffPersonRepository.getOne(id);
            StaffPerson person = modelMapper.map(staffPerson, StaffPerson.class);
            person.setId(id);
            person = staffPersonRepository.saveAndFlush(person);
            return modelMapper.map(person, StaffPersonQueryDTO.class);
        } catch (Exception ex) {
            throw new FootballException(HttpStatus.BAD_REQUEST, "Error in updating person" + ex.getMessage());
        }
    }
}
