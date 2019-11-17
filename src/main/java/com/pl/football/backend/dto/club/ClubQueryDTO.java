package com.pl.football.backend.dto.club;

import com.pl.football.backend.model.Team;
import com.pl.football.backend.model.User;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;


@Data
public class ClubQueryDTO {
    private UUID id;

    @NotNull
    private String clubName;


    private Set<Team> teams;

    @Override
    public String toString() {
        return "ClubQueryDTO{" +
                "id=" + id +
                ", clubName='" + clubName + '\'' +
                ", teams=" + teams.size() +
                '}';
    }
}
