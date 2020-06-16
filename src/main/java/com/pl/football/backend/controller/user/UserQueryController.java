package com.pl.football.backend.controller.user;

import com.pl.football.backend.dto.user.UserQueryDTO;
import com.pl.football.backend.service.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/user")
@Log4j2
public class UserQueryController {

    private final UserService userService;

    @Autowired
    public UserQueryController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserQueryDTO>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.getAllUsers());
    }
    @GetMapping(path = "{id}")
    public ResponseEntity<UserQueryDTO> getUserById(@PathVariable("id") UUID id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.getUserById(id));
    }
}
