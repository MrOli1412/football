package com.pl.football.backend.controller.user;

import com.pl.football.backend.dto.user.UserCreateDTO;
import com.pl.football.backend.dto.user.UserQueryDTO;
import com.pl.football.backend.dto.user.UserUpdateDTO;
import com.pl.football.backend.service.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@Log4j2
public class UserCommandController {
    private final UserService userService;
    @Autowired
    public UserCommandController(UserService userService) {
        this.userService = userService;
    }


    @PutMapping(path = "{id}")
    public ResponseEntity<UserQueryDTO> updateUser(@PathVariable("id")UUID id,@RequestBody UserUpdateDTO userUpdateDTO){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.updateUser(id,userUpdateDTO));
    }

}
