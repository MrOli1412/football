package com.pl.football.backend.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "team")
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String teamName;

    @ManyToOne
    @JoinColumn(name = "Club_ID", nullable = false)
    private Club club;

    @OneToMany
    private Set<Player> players;

    @OneToMany
    private Set<Dress> dresses;

}
