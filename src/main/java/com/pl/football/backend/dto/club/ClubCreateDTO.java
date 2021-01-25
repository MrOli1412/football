package com.pl.football.backend.dto.club;

import com.pl.football.backend.model.User;
import lombok.Data;

import java.util.UUID;

@Data
public class ClubCreateDTO {
    private UUID id;

    private String clubName;

    private UUID pzpnTeamId;


}
