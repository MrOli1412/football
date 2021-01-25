package com.pl.football.backend.model.pzpn;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Table(name = "PZPN_STATE")
@Entity(name = "PZPN_STATE")
@Data
@AllArgsConstructor
public class State implements Serializable {
    private static final long serialVersionUID = -2234691311101756198L;
    @Id
    private Integer id;

    private String stateName;

    public State() {

    }
}
