package com.pl.football.backend.service.staffPerson;

import com.pl.football.backend.dto.staffPerson.StaffPersonCommandDTO;
import com.pl.football.backend.dto.staffPerson.StaffPersonQueryDTO;
import com.pl.football.backend.dto.staffPerson.StaffPersonQueryMatchDTO;
import com.pl.football.backend.model.StaffPerson;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface StaffService {
    StaffPersonQueryDTO savePerson(UUID teamId,StaffPersonCommandDTO staffPerson);

    StaffPersonQueryDTO updatePerson(UUID id, StaffPersonCommandDTO staffPerson);

    StaffPerson getById(UUID id);

    List<StaffPersonQueryMatchDTO> getStaffByTeamId(UUID id);
}
