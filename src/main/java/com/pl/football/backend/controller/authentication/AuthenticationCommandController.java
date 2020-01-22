package com.pl.football.backend.controller.authentication;

import com.pl.football.backend.config.jwt.JwtProvider;
import com.pl.football.backend.dto.user.UserCreateDTO;
import com.pl.football.backend.dto.user.UserLoginDTO;
import com.pl.football.backend.dto.user.UserLoginResponse;
import com.pl.football.backend.model.RoleName;
import com.pl.football.backend.service.club.ClubCommandService;
import com.pl.football.backend.service.role.RoleCommandService;
import com.pl.football.backend.service.user.UserCommandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthenticationCommandController {
    final private AuthenticationManager authenticationManager;

    final private UserCommandService userCommandService;

    final private RoleCommandService roleCommandService;
    private final ClubCommandService clubCommandService;

    final private PasswordEncoder encoder;

    final private JwtProvider jwtProvider;

    public AuthenticationCommandController(AuthenticationManager authenticationManager, UserCommandService userCommandService, RoleCommandService roleCommandService, PasswordEncoder encoder, JwtProvider jwtProvider, ClubCommandService clubCommandService) {
        this.authenticationManager = authenticationManager;
        this.userCommandService = userCommandService;
        this.roleCommandService = roleCommandService;
        this.encoder = encoder;
        this.jwtProvider = jwtProvider;
        this.clubCommandService = clubCommandService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserLoginDTO loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserLoginResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/registry")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserCreateDTO user) {
        if (userCommandService.existsByUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail -> Username is already taken!");
        }

        if (userCommandService.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail -> Email is already in use!");
        }

        user.setPassword(encoder.encode(user.getPassword()));


        user.setRole(roleCommandService.findByName(RoleName.ROLE_ADMIN).get());
        UUID club = clubCommandService.createClub(user.getClub());
        user.getClub().setId(club);
        userCommandService.createUser(user);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Successful");
    }
}
