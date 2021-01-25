package com.pl.football.backend.model.pzpn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Table(name = "PZPN_LEAGUE")
@Entity(name = "PZPN_LEAGUE")
@Data
@AllArgsConstructor
public class League implements Serializable {

    private static final long serialVersionUID = 7421643621592154208L;
    @Id
    private Integer id;

    private String leagueName;

    @ManyToOne
    @JoinColumn(name = "STATE_ID", nullable = false)
    @ToString.Exclude
    State state;

    public League() {

    }
}
