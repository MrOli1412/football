package com.pl.football.backend.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@Table(name = "statistic")
public class Statistic implements Serializable {

    private static final long serialVersionUID = 6783313404189884550L;
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private UUID id;
    private Integer redCards;
    private Integer yellowCards;
    private Integer goals;
    private Integer assist;

    @OneToOne
    @NotNull
    private Player player;

    @OneToOne
    @NotNull
    private Match match;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Statistic)) return false;
        Statistic statistic = (Statistic) o;
        return Objects.equals(id, statistic.id) &&
                Objects.equals(redCards, statistic.redCards) &&
                Objects.equals(yellowCards, statistic.yellowCards) &&
                Objects.equals(goals, statistic.goals) &&
                Objects.equals(assist, statistic.assist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, redCards, yellowCards, goals, assist);
    }
}
