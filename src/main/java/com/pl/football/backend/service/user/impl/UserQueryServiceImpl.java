package com.pl.football.backend.service.user.impl;

import com.pl.football.backend.dto.user.UserQueryDTO;
import com.pl.football.backend.repository.UserRepository;
import com.pl.football.backend.service.user.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    @Autowired
    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserQueryDTO> getAllUsers() {
        return null;
    }

    @Override
    public UserQueryDTO getUserById(UUID id) {
        return null;
    }
}
