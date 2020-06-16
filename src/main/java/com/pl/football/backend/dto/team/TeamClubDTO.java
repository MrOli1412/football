package com.pl.football.backend.dto.team;

import com.pl.football.backend.model.Club;
import com.pl.football.backend.model.Player;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@Data
public class TeamClubDTO {
    private UUID id;
    @NotNull
    private String teamName;
}