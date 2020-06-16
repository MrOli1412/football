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
    private LocalDate matchDate;
    @NotNull
    private String teamName;

    @NotNull
    private UUID dress;

    //        @Size(min = 11, max = 19)
    private Set<PlayerMatchDTO> firstSquad;

    private Set<PlayerMatchDTO> reservedSquad;

    private UUID captainId;

    private UUID firstSquadGoalKeeperId;

    private UUID reservedSquadGoalKeeperId;

    private Boolean isAway;

    private UUID coachId;

    private UUID secondCoachId;

    private UUID masseurId;

    private UUID medicalCarerId;

    private UUID teamManagerId;

    private UUID additionalPersonId;

    private String additionalFunction;
    @NotNull
    private Boolean isFinish;


}
