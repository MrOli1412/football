package com.pl.football.backend.service.player.impl;

import com.pl.football.backend.dto.player.PlayerCreateDTO;
import com.pl.football.backend.dto.player.PlayerFullDataDTO;
import com.pl.football.backend.dto.player.PlayerUpdateDTO;
import com.pl.football.backend.dto.team.TeamQueryDTO;
import com.pl.football.backend.model.Player;
import com.pl.football.backend.model.Team;
import com.pl.football.backend.model.TransferType;
import com.pl.football.backend.repository.PlayerRepository;
import com.pl.football.backend.repository.TeamRepository;
import com.pl.football.backend.service.player.PlayerCommandService;
import com.pl.football.backend.service.team.TeamQueryService;
import lombok.extern.log4j.Log4j2;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Log4j2
public class PlayerCommandServiceImpl implements PlayerCommandService {

    private final PlayerRepository playerRepository;
    private final TeamQueryService teamQueryService;
    private final TeamRepository teamRepository;


    @Autowired
    public PlayerCommandServiceImpl(PlayerRepository playerRepository, TeamQueryService teamQueryService, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamQueryService = teamQueryService;
        this.teamRepository = teamRepository;
    }

    @Override
    public UUID createPlayer(PlayerCreateDTO playerCreateDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Player player = modelMapper.map(playerCreateDTO, Player.class);
        TeamQueryDTO team = teamQueryService.getTeamById(playerCreateDTO.getTeam());
        Team map = modelMapper.map(team, Team.class);
        player.setTeam(map);
        return playerRepository.save(player).getId();
    }

    @Override
    public PlayerFullDataDTO updatePlayer(UUID id, PlayerUpdateDTO playerUpdateDTO) {
        return null;
    }

    @Override
    public Map<String, List<PlayerFullDataDTO>> savePlayersFromFile(UUID teamId, List<PlayerFullDataDTO> playersFromFile) {
        Map<String, List<PlayerFullDataDTO>> playersMap = new HashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        List<Player> players = new ArrayList<>();
        List<PlayerFullDataDTO> newPlayers = new ArrayList<>();
        List<PlayerFullDataDTO> playersInDb = new ArrayList<>();
        Team team = modelMapper.map(teamQueryService.getTeamById(teamId), Team.class);

        playersFromFile.forEach(player -> {

            Player modelPlayer = modelMapper.map(player, Player.class);
            modelPlayer.setTeam(team);

            if (playerRepository.existsByEvidentialNumber(modelPlayer.getEvidentialNumber())) {
                playersInDb.add(player);
            } else {
                players.add(modelPlayer);
                newPlayers.add(player);
            }

        });
        List<Player> players1 = playerRepository.saveAll(players);
        team.getPlayers().addAll(players1);
        teamRepository.save(team);
        playersMap.put("New", newPlayers);
        playersMap.put("In", playersInDb);

        return playersMap;
    }

    public List<PlayerFullDataDTO> convertFile(MultipartFile multipartFile) {
        String REGEX = "[1-9]*.[ ]+(\\S*)[ ]+(\\S*)[ ]+([0-9]*)[ ]+([0-9-]*)[ ]+([0-9-]*)[ ]+(\\S*\\s\\S*\\s\\S*)[ ]([\\S\\s]*)(\\S)+";
        List<PlayerFullDataDTO> playersFromFile = new ArrayList<>();
        try (PDDocument pdDocument = PDDocument.load(multipartFile.getInputStream())) {

            if (!pdDocument.isEncrypted()) {
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);
                PDFTextStripper textStripper = new PDFTextStripper();
                String pdfFileInText = textStripper.getText(pdDocument);
                String lines[] = pdfFileInText.split("\\r?\\n");
                List<String> strings = convertAllLines(lines);
                lines = Arrays.copyOfRange(lines, 2, lines.length);
                Pattern pattern = Pattern.compile(REGEX);
                for (String line : strings) {
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.find()) {
                        playersFromFile.add(new PlayerFullDataDTO(matcher.group(1),
                                matcher.group(2),
                                matcher.group(3),
                                LocalDate.parse(matcher.group(4)),
                                LocalDate.parse(matcher.group(5)),
                                matcher.group(6),
                                matcher.group(7),
                                getNameByCode(matcher.group(8))
                        ));
                    }
                }
            }
            return playersFromFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private List<String> convertAllLines(String[] lines) {


        List<String> clearLines = new ArrayList<>();

        List<String> buffer = new ArrayList<>();

        Pattern startPattern = Pattern.compile("(\\d+\\.)");
        Pattern endPattern = Pattern.compile("(^|[ ])(D|Z|C|W|\\-)$");

        Matcher startMatcher;
        Matcher endMatcher;
        StringBuffer sb = new StringBuffer();

        for (String line : lines) {
            startMatcher = startPattern.matcher(line);
            endMatcher = endPattern.matcher(line);

            if (buffer.isEmpty()) {
                if (startMatcher.find()) {
                    buffer.add(line);
                }

                if (endMatcher.find()) {
                    buffer.forEach(l -> {
                        sb.append(l);
                        sb.append(" ");
                    });
                    buffer.clear();

                    clearLines.add(sb.toString());
                    sb.delete(0, sb.length());
                }
            } else {
                if (endMatcher.find()) {
                    buffer.add(line);
                    buffer.forEach(l -> {
                        sb.append(l);
                        sb.append(" ");
                    });
                    buffer.clear();

                    clearLines.add(sb.toString());
                    sb.delete(0, sb.length());
                } else {
                    buffer.add(line);
                }
            }
        }


        return clearLines;
    }


    private static TransferType getNameByCode(String value) {
        for (TransferType e : TransferType.values()) {
            if (value.equals(e.getValue())) return e;
        }
        return null;
    }
}
