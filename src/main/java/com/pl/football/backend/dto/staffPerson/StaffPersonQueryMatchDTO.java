package com.pl.football.backend.dto.staffPerson;

import com.pl.football.backend.model.Position;
import lombok.Data;

import java.util.UUID;

@Data
public class StaffPersonQueryMatchDTO {
    private UUID id;

    private String firstName;
    private String lastName;
    private String licenseNumber;
    private Position position;
    private String odderFunction;
}
