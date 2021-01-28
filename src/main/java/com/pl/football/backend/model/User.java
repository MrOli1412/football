package com.pl.football.backend.model;

import com.pl.football.backend.model.pzpn.PzpnTeam;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "application_user")
public class User implements Serializable {

    private static final long serialVersionUID = -1767911586955849869L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotNull
    private String username;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
    @OneToOne(fetch = FetchType.LAZY)
    private Role role;
    @OneToOne
    private Setting settings;

    @NotNull
    @OneToOne
    private Club club;
    @NotNull
    @OneToOne
    private PzpnTeam pzpnTeam;

}
