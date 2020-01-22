package com.pl.football.backend.dto.dress;

import com.pl.football.backend.model.Team;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DressCreateDTO {

    @NotNull
    private String color;
    String allowedNumbers;
    @NotNull
    private Team team;
}
