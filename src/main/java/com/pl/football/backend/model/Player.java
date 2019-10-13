package com.pl.football.backend.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Data
@Entity
@Table(name = "player")
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Pattern(regexp = "^[0-9]*$")
    private Integer evidentialNumber;

    @NotNull
    private LocalDate birthDay;

    @NotNull
    private LocalDate contractDate;

    private String amateur;

    private String lastClub;

    private TransferType transferType;

    private LocalDate penaltyStartDate;

    private LocalDate penaltyStopDate;

    @Pattern(regexp = "^[0-9]*$")
    private Integer dressNumber;

    @ManyToOne
    @JoinColumn(name = "Team_ID", nullable = false)
    private Team team;

    @ManyToOne
    @JoinColumn(name = "Match_ID", nullable = false)
    private Match match;

    @Embedded
    Statistic statistic;

    public Optional<@Past LocalDate> getBirthDay() {
        return Optional.of(birthDay);
    }

    public Optional<@Past LocalDate> getContractDate() {
        return Optional.of(contractDate);
    }

    public Optional<@Past LocalDate> getPenaltyStartDate() {
        return Optional.of(penaltyStartDate);
    }

    public Optional<@Past LocalDate> getPenaltyStopDate() {
        return Optional.of(penaltyStopDate);
    }
}
