package com.pl.football.backend.model;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "club")
@ToString
public class Club implements Serializable {

    private static final long serialVersionUID = 4937186966903673941L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String clubName;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "club")
    @ToString.Exclude
    private Set<Team> teams;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Club)) return false;
        Club club = (Club) o;
        return Objects.equals(id, club.id) &&
                Objects.equals(clubName, club.clubName) &&
                Objects.equals(teams, club.teams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clubName, teams);
    }
}
