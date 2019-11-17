package com.pl.football.backend.model;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
@Data
@Entity
@Table(name = "player")
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String evidentialNumber;

    @NotNull
    private LocalDate birthDay;

    @NotNull
    private LocalDate contractDate;

    private String amateur;

    private String lastClub;

    private TransferType transferType;

    private LocalDate penaltyStartDate;

    private LocalDate penaltyStopDate;

    private Integer dressNumber;

    @ManyToOne
    @JoinColumn(name = "Team_ID", nullable = false)
    @ToString.Exclude
    private Team team;

    @ManyToOne
    @JoinColumn(name = "Match_ID")
    @ToString.Exclude
    private Match match;

    @Embedded
    private Statistic statistic;



    public Optional<@Past LocalDate> getBirthDay() {
        return Optional.of(birthDay);
    }

    public Optional<@Past LocalDate> getContractDate() {
        return Optional.of(contractDate);
    }

    public Optional<@Past LocalDate> getPenaltyStartDate() {
        return Optional.ofNullable(penaltyStartDate);
    }

    public Optional<@Past LocalDate> getPenaltyStopDate() {
        return Optional.ofNullable(penaltyStopDate);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id) &&
                Objects.equals(firstName, player.firstName) &&
                Objects.equals(lastName, player.lastName) &&
                Objects.equals(evidentialNumber, player.evidentialNumber) &&
                Objects.equals(birthDay, player.birthDay) &&
                Objects.equals(contractDate, player.contractDate) &&
                Objects.equals(amateur, player.amateur) &&
                Objects.equals(lastClub, player.lastClub) &&
                transferType == player.transferType &&
                Objects.equals(penaltyStartDate, player.penaltyStartDate) &&
                Objects.equals(penaltyStopDate, player.penaltyStopDate) &&
                Objects.equals(dressNumber, player.dressNumber) &&
                Objects.equals(team, player.team) &&
                Objects.equals(match, player.match) &&
                Objects.equals(statistic, player.statistic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, evidentialNumber, birthDay, contractDate, amateur, lastClub, transferType, penaltyStartDate, penaltyStopDate, dressNumber, team, match, statistic);
    }
}
