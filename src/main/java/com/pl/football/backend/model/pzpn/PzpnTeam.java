package com.pl.football.backend.model.pzpn;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Table(name = "PZPN_TEAM")
@Entity(name = "PZPN_TEAM")
@Data
@AllArgsConstructor
public class PzpnTeam implements Serializable {
    private static final long serialVersionUID = 6378761514466229372L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String href;
    private String teamName;


    @ManyToOne
    @JoinColumn(name = "LEAGUE_ID", nullable = false)
    @ToString.Exclude
    League league;

    public PzpnTeam(String href, String teamName, League league) {
        this.href = href;
        this.teamName = teamName;
        this.league = league;
    }

    public PzpnTeam() {

    }
}
