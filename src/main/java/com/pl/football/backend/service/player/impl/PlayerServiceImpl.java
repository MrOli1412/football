package com.pl.football.backend.service.player.impl;

import com.pl.football.backend.dto.player.PlayerFullDataDTO;
import com.pl.football.backend.dto.player.PlayerMatchDTO;
import com.pl.football.backend.dto.player.PlayerShortDTO;
import com.pl.football.backend.dto.player.PlayerUpdateDTO;
import com.pl.football.backend.dto.team.TeamClubDTO;
import com.pl.football.backend.exception.FootballException;
import com.pl.football.backend.model.Player;
import com.pl.football.backend.model.Team;
import com.pl.football.backend.model.TransferType;
import com.pl.football.backend.repository.PlayerRepository;
import com.pl.football.backend.repository.TeamRepository;
import com.pl.football.backend.service.player.PlayerService;
import com.pl.football.backend.service.team.TeamService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PlayerServiceImpl implements PlayerService {


    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;


    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public List<PlayerFullDataDTO> getAllPlayers() {
        return null;
    }

    @Override
    public PlayerFullDataDTO getPlayerById(UUID id) {
        try {
            ModelMapper modelMapper = new ModelMapper();

            return modelMapper.map(playerRepository.getOne(id), PlayerFullDataDTO.class);
        } catch (Exception ex) {
            throw new FootballException(HttpStatus.BAD_REQUEST, "Error ing geting player by id" + ex.getMessage());
        }
    }

    @Override
    public Integer countPlayersFormTeam(UUID teamId) {
        try {
            return playerRepository.countByTeam_Id(teamId).orElse(0);
        } catch (Exception ex) {
            throw new FootballException(HttpStatus.BAD_REQUEST, "Error in couting players " + ex.getMessage());
        }
    }

    @Override
    public List<PlayerShortDTO> getPlayerByTeamId(UUID id) {
        try {


            ModelMapper mapper = new ModelMapper();
            List<PlayerShortDTO> resultPlayers = new ArrayList<>();
            List<Player> playersSet = playerRepository.getByTeam_Id(id).orElse(new ArrayList<>());
            playersSet.forEach(player -> {
                PlayerShortDTO result = mapper.map(player, PlayerShortDTO.class);
                result.setBirthDay(player.getBirthDay().get());
                resultPlayers.add(result);
            });

            return resultPlayers;
        } catch (Exception ex) {
            throw new FootballException(HttpStatus.BAD_REQUEST, "Erro ing geting players by team id" + ex.getMessage());
        }
    }

    @Override
    public List<PlayerMatchDTO> getPlayerMatchInfo(UUID id) {
        try {


            Optional<List<Player>> players = playerRepository.getByTeam_Id(id);
            List<PlayerMatchDTO> result = new ArrayList<>();
            players.ifPresent(playerList -> {
                ModelMapper mapper = new ModelMapper();
                playerList.forEach(player -> {
                    PlayerMatchDTO listElement = mapper.map(player, PlayerMatchDTO.class);
                    listElement.setBirthDay(player.getBirthDay().orElse(null));
                    listElement.setPenaltyStartDate(player.getPenaltyStartDate().orElse(null));
                    listElement.setPenaltyStopDate(player.getPenaltyStopDate().orElse(null));
                    result.add(listElement);
                });
            });
            return result;
        } catch (Exception ex) {
            throw new FootballException(HttpStatus.BAD_REQUEST, "Error in geting player match info " + ex.getMessage());
        }
    }

    @Override
    public UUID createPlayer(UUID teamId, PlayerFullDataDTO playerCreateDTO) {
        try {

            ModelMapper modelMapper = new ModelMapper();
            Player player = modelMapper.map(playerCreateDTO, Player.class);
            Team team = teamRepository.getById(teamId).orElseThrow(() -> new FootballException("Team does not exist"));
            player.setTeam(team);
            return playerRepository.save(player).getId();
        } catch (Exception ex) {
            throw new FootballException(HttpStatus.BAD_REQUEST, "Error in creating player" + ex.getMessage());
        }
    }

    @Override
    public PlayerFullDataDTO updatePlayer(UUID id, PlayerUpdateDTO playerUpdateDTO) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            Player player = modelMapper.map(playerUpdateDTO, Player.class);
            player.setId(id);
            player = playerRepository.saveAndFlush(player);
            return modelMapper.map(player, PlayerFullDataDTO.class);
        } catch (Exception ex) {
            throw new FootballException(HttpStatus.BAD_REQUEST, "Error in updating player" + ex.getMessage());
        }
    }

    @Override
    public Map<String, List<PlayerFullDataDTO>> savePlayersFromFile(UUID teamId, List<PlayerFullDataDTO> playersFromFile) {
        try {


            Map<String, List<PlayerFullDataDTO>> playersMap = new HashMap<>();
            ModelMapper modelMapper = new ModelMapper();
            List<Player> players = new ArrayList<>();
            List<PlayerFullDataDTO> newPlayers = new ArrayList<>();
            List<PlayerFullDataDTO> playersInDb = new ArrayList<>();
            Team team = teamRepository.getById(teamId).orElseThrow(() -> new FootballException("Team does not exist"));
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
            playerRepository.saveAll(players);
            playersMap.put("New", newPlayers);
            playersMap.put("In", playersInDb);

            return playersMap;
        } catch (Exception ex) {
            throw new FootballException(HttpStatus.BAD_REQUEST, "Error in saving players from File");
        }
    }

    public List<PlayerFullDataDTO> convertFile(MultipartFile multipartFile) {
        try {

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
                            playersFromFile.add(new PlayerFullDataDTO(
                                    matcher.group(2),
                                    matcher.group(1),
                                    matcher.group(3),
                                    LocalDate.parse(matcher.group(4)),
                                    LocalDate.parse(matcher.group(5)),
                                    matcher.group(6),
                                    matcher.group(7),
                                    getNameByCode(matcher.group(8)), null
                            ));
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return playersFromFile;
        } catch (Exception ex) {
            throw new FootballException(HttpStatus.BAD_REQUEST, "Error in converting file");
        }
    }


    private List<String> convertAllLines(String[] lines) {


        List<String> clearLines = new ArrayList<>();

        List<String> buffer = new ArrayList<>();

        Pattern startPattern = Pattern.compile("(\\d+\\.)");
        Pattern endPattern = Pattern.compile("(^|[ ])([DZCW\\-])$");

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
