package com.pl.football.backend.service.impl;

import com.pl.football.backend.repository.DressRepository;
import com.pl.football.backend.service.DressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DressServiceImpl implements DressService {

    private final DressRepository dressRepository;

    public DressServiceImpl(DressRepository dressRepository) {
        this.dressRepository = dressRepository;
    }
}
