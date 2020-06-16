package com.pl.football.backend.dto.match;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchShortInfoDTO {

    private UUID id;
    private LocalDate matchDate;
    private Boolean isFinish;
    private Boolean isAway;
    private String teamName;
}
