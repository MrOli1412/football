package com.pl.football.backend.model;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "setting")
public class Setting implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Email
    private String email;

    @Pattern(regexp = "^[0-9]*$")
    private Integer yellowCardLimit;


    @OneToOne
    private User user;
}
