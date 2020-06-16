package com.pl.football.backend.service.match;

import com.pl.football.backend.dto.match.MatchReportDTO;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
public interface MatchReportService {
    Map<String,Object> convertFile(MatchReportDTO matchId) throws IOException;
}
