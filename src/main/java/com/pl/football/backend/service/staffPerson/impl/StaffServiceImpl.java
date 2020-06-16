package com.pl.football.backend.service.staffPerson.impl;

import com.pl.football.backend.dto.staffPerson.StaffPersonCommandDTO;
import com.pl.football.backend.dto.staffPerson.StaffPersonQueryDTO;
import com.pl.football.backend.dto.staffPerson.StaffPersonQueryMatchDTO;
import com.pl.football.backend.exception.FootballException;
import com.pl.football.backend.model.StaffPerson;
import com.pl.football.backend.repository.StaffPersonRepository;
import com.pl.football.backend.service.staffPerson.StaffService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StaffServiceImpl implements StaffService {
    private final StaffPersonRepository staffPersonRepository;

    @Autowired
    public StaffServiceImpl(StaffPersonRepository staffPersonRepository) {
        this.staffPersonRepository = staffPersonRepository;
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
            staffPersonRepository.getByTeamId(id).ifPresent(staffPerson -> {
                result.add(modelMapper.map(staffPerson, StaffPersonQueryMatchDTO.class));

            });
            return result;
        } catch (Exception ex) {
            throw new FootballException(HttpStatus.BAD_REQUEST, "Error ing geting staff by team id" + ex.getMessage());
        }
    }
    @Override
    public StaffPersonQueryDTO savePerson(StaffPersonCommandDTO staffPerson) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            StaffPerson personToSave = modelMapper.map(staffPerson, StaffPerson.class);
            personToSave = staffPersonRepository.saveAndFlush(personToSave);
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
