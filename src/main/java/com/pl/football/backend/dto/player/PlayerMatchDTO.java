package com.pl.football.backend.dto.player;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
@Data
public class PlayerMatchDTO {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String evidentialNumber;

    @NotNull
    private LocalDate birthDay;

    private Integer dressNumber;

}
