package com.pl.football.backend.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "team")
@ToString
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String teamName;

    @ManyToOne
    @JoinColumn(name = "Club_ID", nullable = false)
    @ToString.Exclude
    private Club club;

    @OneToMany
    @ToString.Exclude
    private Set<Player> players;

    @OneToMany
    @ToString.Exclude
    private Set<Dress> dresses;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id) &&
                Objects.equals(teamName, team.teamName) &&
                Objects.equals(dresses, team.dresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teamName, dresses);
    }
}
