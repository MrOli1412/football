package com.pl.football.backend.service.user.impl;

import com.pl.football.backend.dto.club.ClubCreateDTO;
import com.pl.football.backend.dto.user.UserCreateDTO;
import com.pl.football.backend.dto.user.UserQueryDTO;
import com.pl.football.backend.dto.user.UserUpdateDTO;
import com.pl.football.backend.exception.FootballException;
import com.pl.football.backend.model.User;
import com.pl.football.backend.repository.UserRepository;
import com.pl.football.backend.service.club.ClubService;
import com.pl.football.backend.service.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ClubService clubService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ClubService clubService) {
        this.userRepository = userRepository;
        this.clubService = clubService;
    }

    @Transactional
    @Override
    public UUID createUser(UserCreateDTO userCreateDTO, ClubCreateDTO club) {
        try {
            UUID clubId = clubService.createClub(club);
            userCreateDTO.getClub().setId(clubId);
            ModelMapper modelMapper = new ModelMapper();
            User user = modelMapper.map(userCreateDTO, User.class);
            user = userRepository.save(user);
            return user.getId();
        } catch (Exception ex) {
            throw new FootballException(HttpStatus.BAD_REQUEST, "Error in creat user" + ex.getMessage());
        }
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


    @Override
    public List<UserQueryDTO> getAllUsers() {
        return null;
    }

    @Override
    public UserQueryDTO getUserById(UUID id) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(userRepository.findById(id).orElseThrow(() ->new FootballException(" user does not exist")), UserQueryDTO.class);
        } catch (Exception ex) {
            throw new FootballException(HttpStatus.BAD_REQUEST, "Error in get user by id" + ex.getMessage());
        }

    }
}
