package com.pl.football.backend.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "club")
public class Club implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String clubName;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "club")
    private Set<Team> teams;




}
