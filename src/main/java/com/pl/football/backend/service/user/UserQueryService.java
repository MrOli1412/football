package com.pl.football.backend.service.user;

import com.pl.football.backend.dto.user.UserQueryDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface UserQueryService {
    List<UserQueryDTO> getAllUsers();

    UserQueryDTO getUserById(UUID id);
}
