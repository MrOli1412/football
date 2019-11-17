package com.pl.football.backend.dto.player;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class PlayerCreateDTO {

    private UUID id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Pattern(regexp = "^[0-9]*$")
    private Integer evidentialNumber;

    @NotNull
    private LocalDate birthDay;

    @NotNull
    private LocalDate contractDate;

    @NotNull
    private UUID team;
}
