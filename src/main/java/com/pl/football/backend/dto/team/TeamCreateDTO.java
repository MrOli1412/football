package com.pl.football.backend.dto.team;

import com.pl.football.backend.model.Club;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class TeamCreateDTO {

    private UUID id;

    @NotNull
    private String teamName;

    @NotNull
    private UUID club;
}
