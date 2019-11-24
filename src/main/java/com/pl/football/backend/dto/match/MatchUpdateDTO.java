package com.pl.football.backend.dto.match;

import com.pl.football.backend.dto.player.PlayerMatchDTO;
import com.pl.football.backend.model.Dress;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Data
public class MatchUpdateDTO {
    @NotNull
    LocalDate matchDate;

    @NotNull
    Dress dress;

    @Size(min = 11, max = 19)
    Set<PlayerMatchDTO> players;

    @NotNull
    Boolean isFinish;
}
