package com.pl.football.backend.dto.player;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Data
public class PlayerShortDTO {
    @NotNull
    private UUID id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String evidentialNumber;

    @NotNull
    private LocalDate birthDay;



}
