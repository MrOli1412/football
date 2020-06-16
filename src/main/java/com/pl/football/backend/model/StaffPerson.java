package com.pl.football.backend.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "staffPerson")
public class StaffPerson implements Serializable {
    private static final long serialVersionUID = -1231056271432744391L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String firstName;
    private String lastName;
    private String licenseNumber;
    private Boolean isDefault;
    private Position position;
    private String odderFunction;

    @ManyToOne()
    @JoinColumn(name = "TEAM_ID", nullable = false)
    @ToString.Exclude
    private Team team;
}
