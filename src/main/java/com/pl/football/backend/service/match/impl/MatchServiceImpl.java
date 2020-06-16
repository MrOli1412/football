package com.pl.football.backend.service.match.impl;

import com.pl.football.backend.dto.dress.DressQueryDTO;
import com.pl.football.backend.dto.match.MatchQueryDTO;
import com.pl.football.backend.dto.match.MatchReportDTO;
import com.pl.football.backend.dto.match.MatchShortInfoDTO;
import com.pl.football.backend.dto.match.MatchUpdateDTO;
import com.pl.football.backend.dto.player.PlayerMatchDTO;
import com.pl.football.backend.dto.staffPerson.StaffPersonQueryMatchDTO;
import com.pl.football.backend.exception.FootballException;
import com.pl.football.backend.model.*;
import com.pl.football.backend.repository.*;
import com.pl.football.backend.service.dress.DressService;
import com.pl.football.backend.service.match.MatchService;
import com.pl.football.backend.service.player.PlayerService;
import com.pl.football.backend.service.staffPerson.StaffService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatchServiceImpl implements MatchService {
    private final MatchRepository matchRepository;
    private final PlayerService playerService;
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final DressRepository dressRepository;
    private final StaffPersonRepository staffPersonRepository;
    private final DressService dressService;
    private StaffService staffService;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository, PlayerService playerService, PlayerRepository playerRepository, TeamRepository teamRepository, DressRepository dressRepository, StaffPersonRepository staffPersonRepository, DressService dressService, StaffService staffService) {
        this.matchRepository = matchRepository;
        this.playerService = playerService;
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.dressRepository = dressRepository;
        this.staffPersonRepository = staffPersonRepository;
        this.dressService = dressService;
        this.staffService = staffService;
    }

    @Override
    public UUID createMatch(UUID teamId, MatchReportDTO match) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            Set<Player> playerToSave = new HashSet<>();
            Set<PlayerMatchDTO> matchPlayers = new HashSet<>();
            Set<StaffPerson> staffPeople = new HashSet<>();
            matchPlayers.addAll(match.getFirstSquad());
            matchPlayers.addAll(match.getReservedSquad());
            Team team = teamRepository.getOne(teamId);
            Dress dress = dressRepository.getOne(match.getDress());

            matchPlayers.forEach(playerDto -> {
                playerToSave.add(playerRepository.findById(playerDto.getId()).orElseThrow(() -> new FootballException("Player does not exist")));
            });
            match.getStaffPeople().forEach(staff -> {
                staffPeople.add(staffPersonRepository.findById(staff.getId()).orElseThrow(()-> new FootballException("Staff Person does not exist")));
            });
            modelMapper.getConfiguration().setFieldMatchingEnabled(true);
            Match result = modelMapper.map(match, Match.class);
            result.setPlayers(playerToSave);
            result.setTeam(team);
            result.setDress(dress);
            result.setStaffPeople(staffPeople);
            return matchRepository.saveAndFlush(result).getId();
        } catch (Exception ex) {
            throw new FootballException(HttpStatus.BAD_REQUEST, "Error creating match" + ex.getMessage());
        }
    }

    @Override
    public MatchQueryDTO updateMatch(UUID id, MatchUpdateDTO matchUpdateDTO) {
        return null;
    }

//
//    private Set<StaffPerson> prepareStaffPersons(MatchReportDTO match) {
//        Set<PlayerMatchDTO> matchPlayers = new HashSet<>();
//

////        return
//    }

    @Override
    public List<MatchQueryDTO> getAllMatch() {
        return null;
    }

    @Override
    public List<MatchShortInfoDTO> getMatchByTeamId(UUID id) {

        try {
            List<MatchShortInfoDTO> response = new ArrayList<>();
            ModelMapper modelMapper = new ModelMapper();
             matchRepository.getByTeam_Id(id).ifPresent(team -> {
                response.add(modelMapper.map(team, MatchShortInfoDTO.class));
            });
            return response;
        } catch (Exception ex) {
            throw new FootballException(HttpStatus.BAD_REQUEST, "Error in get match list for team" + ex.getMessage());
        }

    }

    @Override
    public Integer countMatchesFromTeam(UUID teamId) {
        return matchRepository.countByTeam_Id(teamId).orElse(0);
    }

    @Override
    public MatchQueryDTO getInfoToFrom(UUID teamId) {
        try {
            List<PlayerMatchDTO> players = playerService.getPlayerMatchInfo(teamId);
            List<DressQueryDTO> dressForTeam = dressService.getDressForTeam(teamId);
            List<StaffPersonQueryMatchDTO> staffPersons = staffService.getStaffByTeamId(teamId);
            return new MatchQueryDTO(dressForTeam, players, staffPersons);
        } catch (Exception ex) {
            throw new FootballException(HttpStatus.BAD_REQUEST, "Error ing geting info from form" + ex.getMessage());
        }
    }
}


