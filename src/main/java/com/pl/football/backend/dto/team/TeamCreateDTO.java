package com.pl.football.backend.dto.team;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class TeamCreateDTO {


    @NotNull
    private String teamName;

    @NotNull
    private boolean isGeneratedFromPZPN;

}
