package com.pl.football.backend.service.user;

import com.pl.football.backend.dto.user.UserCreateDTO;
import com.pl.football.backend.dto.user.UserQueryDTO;
import com.pl.football.backend.dto.user.UserUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service

public interface UserCommandService {
    UUID createUser(UserCreateDTO userCreateDTO);

    UserQueryDTO updateUser(UUID id, UserUpdateDTO userUpdateDTO);
}
