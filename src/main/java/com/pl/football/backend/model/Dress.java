package com.pl.football.backend.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

@Data
@Entity
@Table(name = "dressColor")
public class Dress implements Serializable {
    private static final long serialVersionUID = 7058196715105640613L;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    UUID id;

    @NotNull
    private  String color;
    Integer[] allowedNumbers;
    @ManyToOne
    @JoinColumn(name = "Team_ID",nullable = false)
    private Team team;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dress)) return false;
        Dress dress = (Dress) o;
        return Objects.equals(id, dress.id) &&
                Objects.equals(color, dress.color) &&
                Arrays.equals(allowedNumbers, dress.allowedNumbers);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, color);
        result = 31 * result + Arrays.hashCode(allowedNumbers);
        return result;
    }
}
