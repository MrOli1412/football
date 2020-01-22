package com.pl.football.backend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotNull;
import java.util.Collection;

@Data

public class UserLoginResponse {
    @NotNull
    private String token;
    private String type = "Bearer";
    @NotNull
    private String username;
    @NotNull
    private Collection<? extends GrantedAuthority> authorize;

    public UserLoginResponse(@NotNull String token, @NotNull String username, @NotNull Collection<? extends GrantedAuthority> authorize) {
        this.token = token;
        this.username = username;
        this.authorize = authorize;
    }
}
