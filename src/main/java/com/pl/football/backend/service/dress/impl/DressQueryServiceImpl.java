package com.pl.football.backend.service.dress.impl;

import com.pl.football.backend.dto.dress.DressQueryDTO;
import com.pl.football.backend.model.Dress;
import com.pl.football.backend.repository.DressRepository;
import com.pl.football.backend.service.dress.DressQueryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        ModelMapper modelMapper = new ModelMapper();
        List<DressQueryDTO> dressList = new ArrayList<>();
        List<Dress> all = this.dressRepository.findAll();
        all.forEach(dress -> {
            dressList.add(modelMapper.map(dress, DressQueryDTO.class));
        });
        return dressList;
    }

    @Override
    public DressQueryDTO getDressById(UUID id) {
        ModelMapper modelMapper = new ModelMapper();
        Optional<Dress> byId = this.dressRepository.findById(id);
        return byId.map(dress -> modelMapper.map(dress, DressQueryDTO.class)).orElse(null);

    }
}
