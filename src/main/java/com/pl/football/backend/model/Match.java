package com.pl.football.backend.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "match")
public class Match implements Serializable {
    private static final long serialVersionUID = -6228948858682736245L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private LocalDate matchDate;

    @NotNull
    @OneToOne
    @ToString.Exclude
    private Dress dress;

    @OneToMany
    @Size(min = 11, max = 19)
    @ToString.Exclude
    @JoinColumn
    private Set<Player> players;

    private Boolean isFirstSquadPlayer;

    @NotNull
    private Boolean isFinish;

    private Boolean isAway;

    private String teamName;

    @OneToOne
    @JoinColumn(name = "Team_ID", nullable = false)
    @ToString.Exclude
    private Team team;

    @OneToOne
    private Player captain;
    @OneToOne
    private Player firstSquadGoalKeeper;
    @OneToOne
    private Player reservedSquadGoalKeeper;

    @OneToMany
    @ToString.Exclude
    @JoinColumn
    private Set<StaffPerson> staffPeople;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Match)) return false;
        Match match = (Match) o;
        return Objects.equals(id, match.id) &&
                Objects.equals(matchDate, match.matchDate) &&
                Objects.equals(isFinish, match.isFinish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, matchDate, isFinish);
    }

}
