package com.pl.football.backend.dto.match;

import com.pl.football.backend.dto.player.PlayerMatchDTO;
import com.pl.football.backend.model.Dress;
import com.pl.football.backend.model.Player;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Data
public class MatchQueryDTO {
    LocalDate matchDate;

    Dress dress;

    Set<PlayerMatchDTO> players;

    Boolean isFinish;
}
