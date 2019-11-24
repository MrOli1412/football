package com.pl.football.backend.service.dress;

import com.pl.football.backend.dto.dress.DressCreateDTO;
import com.pl.football.backend.dto.dress.DressQueryDTO;
import com.pl.football.backend.dto.dress.DressUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface DressCommandService {
    UUID createDress(UUID teamId, DressCreateDTO dressCreateDTO);

    DressQueryDTO updateDress(UUID id, DressUpdateDTO dressUpdateDTO);
}
