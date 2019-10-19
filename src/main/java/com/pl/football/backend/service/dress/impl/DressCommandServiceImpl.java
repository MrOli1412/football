package com.pl.football.backend.service.dress.impl;

import com.pl.football.backend.dto.dress.DressCreateDTO;
import com.pl.football.backend.dto.dress.DressQueryDTO;
import com.pl.football.backend.dto.dress.DressUpdateDTO;
import com.pl.football.backend.repository.DressRepository;
import com.pl.football.backend.service.dress.DressCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DressCommandServiceImpl implements DressCommandService {

    private final DressRepository dressRepository;
    @Autowired
    public DressCommandServiceImpl(DressRepository dressRepository) {
        this.dressRepository = dressRepository;
    }

    @Override
    public UUID createDress(DressCreateDTO dressCreateDTO) {
        return null;
    }

    @Override
    public DressQueryDTO updateDress(UUID id, DressUpdateDTO dressUpdateDTO) {
        return null;
    }
}