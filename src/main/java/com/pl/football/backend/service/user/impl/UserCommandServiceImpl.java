package com.pl.football.backend.service.user.impl;

import com.pl.football.backend.dto.user.UserCreateDTO;
import com.pl.football.backend.dto.user.UserQueryDTO;
import com.pl.football.backend.dto.user.UserUpdateDTO;
import com.pl.football.backend.repository.UserRepository;
import com.pl.football.backend.service.user.UserCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;

    @Autowired
    public UserCommandServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UUID createUser(UserCreateDTO userCreateDTO) {
        return null;
    }

    @Override
    public UserQueryDTO updateUser(UUID id, UserUpdateDTO userUpdateDTO) {
        return null;
    }
}
