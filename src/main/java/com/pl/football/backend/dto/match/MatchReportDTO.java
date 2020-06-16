package com.pl.football.backend.dto.match;

import com.pl.football.backend.dto.player.PlayerMatchDTO;
import com.pl.football.backend.dto.staffPerson.StaffPersonQueryMatchDTO;
import com.pl.football.backend.model.StaffPerson;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
public class MatchReportDTO {
    @NotNull
    private LocalDate matchDate;
    @NotNull
    private String teamName;

    @NotNull
    private UUID dress;

    //        @Size(min = 11, max = 19)
    private Set<PlayerMatchDTO> firstSquad;

    private Set<PlayerMatchDTO> reservedSquad;

    private UUID captain;

    private UUID firstSquadGoalKeeper;

    private UUID reservedSquadGoalKeeper;

    private Boolean isAway;

    private Set<StaffPersonQueryMatchDTO> staffPeople;

    @NotNull
    private Boolean isFinish;


}
