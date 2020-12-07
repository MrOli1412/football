package com.pl.football.backend.dto.staffPerson;

import com.pl.football.backend.model.Position;
import lombok.Data;

@Data
public class StaffPersonQueryDTO {
    private String firstName;
    private String lastName;
    private String licenseNumber;
    private Position position;
    private String odderFunction;
}
