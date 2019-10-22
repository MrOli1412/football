package com.pl.football.backend.dto.club;

import com.pl.football.backend.model.Team;
import com.pl.football.backend.model.User;
import lombok.Data;

import java.util.Set;
import java.util.UUID;


@Data
public class ClubQueryDTO {
    private UUID id;

    private String clubName;

    private User user;

    private Set<Team> teams;

    @Override
    public String toString() {
        return "ClubQueryDTO{" +
                "id=" + id +
                ", clubName='" + clubName + '\'' +
                ", user=" + user.getUsername() +
                ", teams=" + teams.size() +
                '}';
    }
}
