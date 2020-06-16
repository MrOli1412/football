package com.pl.football.backend.dto.staffPerson;

import com.pl.football.backend.model.Position;
import lombok.Data;

import java.util.UUID;

@Data
public class StaffPersonCommandDTO {
    private UUID id;

    private String firstName;
    private String lastName;
    private String licenseNumber;
    private Boolean isDefault;
    private Position position;
    private String odderFunction;
}
