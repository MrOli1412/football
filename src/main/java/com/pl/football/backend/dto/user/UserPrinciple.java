package com.pl.football.backend.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pl.football.backend.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;

@Data
public class UserPrinciple implements UserDetails {
    private static final long serialVersionUID = 1L;

    private UUID id;

    private String name;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    private GrantedAuthority authorities;

    public UserPrinciple(UUID id,
                         String username, String email, String password,
                         GrantedAuthority authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrinciple build(User user) {
        GrantedAuthority authorities = new SimpleGrantedAuthority(user.getRole().getName().name());


        return new UserPrinciple(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }



    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(authorities);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username, email, password, authorities);
    }
}
