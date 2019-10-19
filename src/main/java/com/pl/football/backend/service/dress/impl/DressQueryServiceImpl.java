package com.pl.football.backend.service.dress.impl;

import com.pl.football.backend.dto.dress.DressQueryDTO;
import com.pl.football.backend.repository.DressRepository;
import com.pl.football.backend.service.dress.DressQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DressQueryServiceImpl implements DressQueryService {

    private final DressRepository dressRepository;

    @Autowired
    public DressQueryServiceImpl(DressRepository dressRepository) {
        this.dressRepository = dressRepository;
    }

    @Override
    public List<DressQueryDTO> getAllDresses() {
        return null;
    }

    @Override
    public DressQueryDTO getDressById(UUID id) {
        return null;
    }
}
