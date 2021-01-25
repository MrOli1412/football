package com.pl.football.backend.model;

import com.pl.football.backend.model.pzpn.PzpnTeam;
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

    private static final long serialVersionUID = 3439373085649272598L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String teamName;

    @ManyToOne
    @JoinColumn(name = "Club_ID", nullable = false)
    @ToString.Exclude
    private Club club;

    @OneToOne
    @ToString.Exclude
    private PzpnTeam pzpnTeam;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team")
    @ToString.Exclude
    private Set<Player> players;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team")
    @ToString.Exclude
    private Set<Dress> dresses;

    @ManyToMany()
    @JoinTable(
            name = "team_staff",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "staff_id"))
    @ToString.Exclude
    private Set<StaffPerson> staffPeople;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id) &&
                Objects.equals(teamName, team.teamName);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teamName);
    }
}
