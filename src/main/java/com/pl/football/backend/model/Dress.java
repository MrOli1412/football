package com.pl.football.backend.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "dressColor")
public class Dress implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    UUID id;

    @NotNull
    private  String color;
    Integer[] allowedNumbers;
    @ManyToOne
    @JoinColumn(name = "Team_ID",nullable = false)
    private Team team;
}
