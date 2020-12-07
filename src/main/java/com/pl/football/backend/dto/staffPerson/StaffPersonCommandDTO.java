package com.pl.football.backend.dto.staffPerson;

import com.pl.football.backend.model.Position;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class StaffPersonCommandDTO {
    private UUID id;

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String licenseNumber;
    private Position position;
    private String odderFunction;
}
