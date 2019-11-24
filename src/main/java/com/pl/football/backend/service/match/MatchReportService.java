package com.pl.football.backend.service.match;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
public interface MatchReportService {
    void convertFile(UUID matchId) throws IOException;
}
