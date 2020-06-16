package com.pl.football.backend.service.user;

import com.pl.football.backend.dto.club.ClubCreateDTO;
import com.pl.football.backend.dto.user.UserCreateDTO;
import com.pl.football.backend.dto.user.UserQueryDTO;
import com.pl.football.backend.dto.user.UserUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface UserService {
    UUID createUser(UserCreateDTO userCreateDTO,  ClubCreateDTO club);

    UserQueryDTO updateUser(UUID id, UserUpdateDTO userUpdateDTO);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    List<UserQueryDTO> getAllUsers();

    UserQueryDTO getUserById(UUID id);


}
