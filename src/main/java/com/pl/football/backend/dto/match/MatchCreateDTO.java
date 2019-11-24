package com.pl.football.backend.dto.match;

import com.pl.football.backend.dto.player.PlayerMatchDTO;
import com.pl.football.backend.model.Dress;
import com.pl.football.backend.model.Player;
import com.pl.football.backend.model.Team;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
public class MatchCreateDTO {
    @NotNull
    LocalDate matchDate;

    @NotNull
    UUID dress;

    @Size(min = 11, max = 19)
    Set<PlayerMatchDTO> players;

    @NotNull
    Boolean isFinish = false;

    @NotNull
    Team team;

}
