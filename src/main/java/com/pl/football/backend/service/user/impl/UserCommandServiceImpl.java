package com.pl.football.backend.service.user.impl;

import com.pl.football.backend.dto.user.UserCreateDTO;
import com.pl.football.backend.dto.user.UserQueryDTO;
import com.pl.football.backend.dto.user.UserUpdateDTO;
import com.pl.football.backend.model.User;
import com.pl.football.backend.repository.UserRepository;
import com.pl.football.backend.service.user.UserCommandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userCreateDTO, User.class);
        user = userRepository.save(user);
        return user.getId();

    }

    @Override
    public UserQueryDTO updateUser(UUID id, UserUpdateDTO userUpdateDTO) {
        return null;
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }




}
