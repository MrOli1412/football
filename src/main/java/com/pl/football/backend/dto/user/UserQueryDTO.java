package com.pl.football.backend.dto.user;

import com.pl.football.backend.model.Club;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class UserQueryDTO {
    @NotNull
    private String username;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    @NotNull
    private Club club;

}
