package com.pl.football.backend.dto.user;

import com.pl.football.backend.dto.club.ClubCreateDTO;
import com.pl.football.backend.model.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class UserCreateDTO {


    @NotNull
    private String username;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    private Role role;

    @NotNull
    private ClubCreateDTO club;



    public UserCreateDTO(@NotNull String username, @NotNull @Email String email, @NotNull String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
