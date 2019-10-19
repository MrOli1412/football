package com.pl.football.backend.service.dress;

import com.pl.football.backend.dto.dress.DressQueryDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface DressQueryService {
    List<DressQueryDTO> getAllDresses();

    DressQueryDTO getDressById(UUID id);
}
