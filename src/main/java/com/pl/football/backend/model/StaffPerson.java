package com.pl.football.backend.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
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
    private Position position;
    private String odderFunction;

    @ManyToMany(mappedBy = "staffPeople")
    @ToString.Exclude
    private Set<Team> team;



}
