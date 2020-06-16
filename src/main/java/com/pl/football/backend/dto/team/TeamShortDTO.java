package com.pl.football.backend.dto.team;

import lombok.Data;

@Data
public class TeamShortDTO {
    String teamName;
    Integer countPlayers;
    Integer countMatches;
    Integer countDress;
}
