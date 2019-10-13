package com.pl.football.backend.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "match")
public class Match implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    @NotNull
    LocalDate matchDate;

    @NotNull
    Dress dress;

    @OneToMany
    @Size(min = 11,max = 19)
    Set<Player> players;

    @NotNull
    Boolean isFinish;


}
