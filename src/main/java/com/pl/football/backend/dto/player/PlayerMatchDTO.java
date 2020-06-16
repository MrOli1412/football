package com.pl.football.backend.dto.player;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class PlayerMatchDTO {
    @NotNull
    UUID id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private LocalDate birthDay;

    private Integer dressNumber;

    private LocalDate penaltyStartDate;

    private LocalDate penaltyStopDate;

}
