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
    LocalDate matchDate;

    @NotNull
    @OneToOne
    @ToString.Exclude
    Dress dress;

    @OneToMany
    @Size(min = 11, max = 19)
    @ToString.Exclude
    Set<Player> players;

    @NotNull
    Boolean isFinish;

    @OneToOne
    @ToString.Exclude
    Team team;
//    @Embedded
//    MatchStatistic matchStatistic;

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
