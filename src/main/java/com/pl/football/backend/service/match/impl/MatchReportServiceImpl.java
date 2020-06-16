package com.pl.football.backend.service.match.impl;

import com.pl.football.backend.dto.match.MatchReportDTO;
import com.pl.football.backend.dto.player.PlayerMatchDTO;
import com.pl.football.backend.dto.staffPerson.StaffPersonQueryMatchDTO;
import com.pl.football.backend.exception.FootballException;
import com.pl.football.backend.service.dress.DressService;
import com.pl.football.backend.service.match.MatchReportService;
import com.pl.football.backend.service.match.MatchService;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * paragraph id 2 - druzyna,kolor stroi,data
 * <p>
 * zawodnicy:
 * cell 0  - funkcja
 * cell 1 - numer
 * cell 2-23 - nazwisko i imie
 * cell 25-30 - data urodzenia dd-mm-rr
 * <p>
 * obsluga:
 * cell 1-20 - nazwisko imie
 * cell 22  - nr licencji trenera(pierwszy row)
 * cell 22 -31 - funkcja (ostatni row)
 */

@Service
@Log4j2
public class MatchReportServiceImpl implements MatchReportService {
    int i = 0;
    private final MatchService matchService;
    private final DressService dressService;
    private boolean isGoalKeeperCaptain;
    private List<XWPFTableRow> staffTableRows;

    @Autowired
    public MatchReportServiceImpl(MatchService matchService, DressService dressService) {
        this.matchService = matchService;
        this.dressService = dressService;
    }

    @Override
    public Map<String, Object> convertFile(MatchReportDTO match) throws IOException {
        try {
            File msWordPath;
            if (match.getIsAway()) {
                msWordPath = new ClassPathResource("/static/sprAway.docx").getFile();
            } else {
                msWordPath = new ClassPathResource("/static/sprHome.docx").getFile();
            }
            XWPFDocument document = new XWPFDocument(new FileInputStream(msWordPath));
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            List<IBodyElement> bodyElements = document.getBodyElements();
            List<XWPFTable> tables = document.getTables();
            XWPFTable playersTable = tables.get(0);
            XWPFTable staffTable = tables.get(1);

            setMatchData(paragraphs.get(1), match);
            setPlayers(playersTable, match, paragraphs);
            setStaff(staffTable, match);
            String fileName = match.getTeamName().concat(match.getMatchDate().toString()) + ".docx";
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            document.write(out);
            out.close();
            document.close();
            Map<String, Object> response = new HashMap<>();
            response.put("fileName", fileName);
            response.put("file", out);
            return response;
        } catch (Exception ex) {
            throw new FootballException(HttpStatus.BAD_REQUEST, "Error in creating report"+ ex.getMessage());
        }

    }

    private void setMatchData(XWPFParagraph paragraph, MatchReportDTO match) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String format = dateTimeFormatter.format(match.getMatchDate());
        String text = paragraph.getText();
        String[] split = text.split(":");
        split[0] = split[0] + ": " + match.getTeamName();
        split[1] = split[1] + ": " + dressService.getDressById(match.getDress()).getColor();
        split[2] = split[2] + ": " + format;
        for (int i = paragraph.getRuns().size() - 1; i >= 0; i--) {
            paragraph.removeRun(i);
        }
        setRun(paragraph.createRun(), "Calibri", 9, split[0] + "\t" + split[1] + "\t" + split[2], true, false);
    }

    private void setCaptainNumber(XWPFParagraph paragraph, int number) {
        List<XWPFRun> runs = paragraph.getRuns();
        XWPFRun xwpfRun = runs.get(1);
        setRun(xwpfRun, "Calibri", 9, number != 0 ? " \t" + number + " \t" : " \t  \t", true, false);
    }

    private void setPlayers(XWPFTable table, MatchReportDTO match, List<XWPFParagraph> paragraphs) {


        List<XWPFTableRow> firstSquadRows = table.getRows().subList(2, 13);
        List<XWPFTableRow> reservedSquadRows = table.getRows().subList(14, 21);
        PlayerMatchDTO captain = match.getFirstSquad().stream().filter(ply -> ply.getId().equals(match.getCaptain())).findAny().orElse(null);
        PlayerMatchDTO firstGoalKeeper = match.getFirstSquad().stream().filter(ply -> ply.getId().equals(match.getFirstSquadGoalKeeper())).findAny().orElse(null);
        PlayerMatchDTO secondGoalKeeper = match.getReservedSquad().stream().filter(ply -> ply.getId().equals(match.getReservedSquadGoalKeeper())).findAny().orElse(null);
        List<PlayerMatchDTO> firstSquad = new ArrayList<>(match.getFirstSquad());
        if (firstGoalKeeper != null) {
            Collections.swap(firstSquad, 0, firstSquad.indexOf(firstGoalKeeper));
        }
        if (captain != null) {
            setCaptainNumber(paragraphs.get(5), captain.getDressNumber() != null ? captain.getDressNumber() : 0);
            if (firstGoalKeeper != null) {
                isGoalKeeperCaptain = captain.equals(firstGoalKeeper);
            }
        }
        if (!isGoalKeeperCaptain && captain != null) {
            Collections.swap(firstSquad, 1, firstSquad.indexOf(captain));

        }
        List<PlayerMatchDTO> reservedSquad = new ArrayList<>(match.getReservedSquad());
        if (secondGoalKeeper != null) {
            Collections.swap(reservedSquad, 0, reservedSquad.indexOf(secondGoalKeeper));
        }
        for (int i = 0; i < firstSquad.size(); i++) {
            setPlayersData(firstSquad.get(i), firstSquadRows.get(i), captain);
        }
        for (int i = 0; i < reservedSquad.size(); i++) {
            setPlayersData(reservedSquad.get(i), reservedSquadRows.get(i), captain);
        }

//
//        rows.forEach(row -> {
//            setPlayersData(format, playerList, row,capt);
//        });
    }

    private void setPlayersData(PlayerMatchDTO player, XWPFTableRow row, PlayerMatchDTO captain) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("ddMMyy");
        char[] format = dateTimeFormatter.format(player.getBirthDay()).toCharArray();
        List<XWPFTableCell> playerCells = row.getTableCells();
        XWPFParagraph addParagraph = playerCells.get(0).getParagraphArray(0);
        XWPFParagraph addParagraph2 = playerCells.get(1).getParagraphArray(0);
        XWPFRun run = addParagraph.createRun();
        XWPFRun run2 = addParagraph2.createRun();
        if (player.equals(captain)) {
            if (isGoalKeeperCaptain) {
                addParagraph.getRuns().get(0).setFontSize(8);
                setRun(run, "Calibri", 8, "K", true, false);
            } else {
                setRun(run, "Calibri", 8, "K", true, false);

            }
        }
        setRun(run2, "Calibri", 8, player.getDressNumber() != null ? player.getDressNumber().toString() : "", false, false);
        String playerData = player.getLastName().toUpperCase() + " " + player.getFirstName().toUpperCase();
        for (int i = 2, textIterator = 0; textIterator < playerData.length() && i <= 23; i++, textIterator++) {
            XWPFParagraph p = playerCells.get(i).getParagraphArray(0);
            XWPFRun r = p.createRun();
            setRun(r, "Calibri", 8, String.valueOf(playerData.toCharArray()[textIterator]), false, false);
        }
        for (int i = 25, textIterator = 0; textIterator < format.length && i <= 30; i++, textIterator++) {
            XWPFParagraph p = playerCells.get(i).getParagraphArray(0);
            XWPFRun r = p.createRun();
            setRun(r, "Calibri", 8, String.valueOf(format[textIterator]), false, false);
        }

    }

    private void setStaff(XWPFTable table, MatchReportDTO match) {
        staffTableRows = table.getRows().subList(1, 7);
        Set<StaffPersonQueryMatchDTO> staffPeople = match.getStaffPeople();
        staffPeople.forEach(this::setStaffData);
    }

    private void setStaffData(StaffPersonQueryMatchDTO staff) {
        String staffData = staff.getLastName().toUpperCase() + " " + staff.getFirstName().toUpperCase();
        if (staff.getPosition() != null) {
            switch (staff.getPosition()) {
                case COACH:
                    processRow(staffData, 0);
                    processLicenceNumber(staff.getLicenseNumber());
                    break;
                case SECOND_COACH:
                    processRow(staffData, 1);
                    break;
                case MASSEUR:
                    processRow(staffData, 2);
                    break;
                case MEDICAL_CARER:
                    processRow(staffData, 3);
                    break;
                case TEAM_MANAGER:
                    processRow(staffData, 4);
                    break;

            }
        } else if (staff.getOdderFunction() != null) {
            processRow(staffData, 5);
            processAdditionalFunction(staff.getOdderFunction().toUpperCase(), 5);
        }

    }

    private void processRow(String staffData, int row) {
        List<XWPFTableCell> playerCells = staffTableRows.get(row).getTableCells();
        for (int i = 1, textIterator = 0; textIterator < staffData.length() && i <= 20; i++, textIterator++) {
            XWPFParagraph p = playerCells.get(i).getParagraphArray(0);
            XWPFRun r = p.createRun();
            setRun(r, "Calibri", 8, String.valueOf(staffData.toCharArray()[textIterator]), false, false);
        }
    }

    private void processLicenceNumber(String licenceNumber) {
        List<XWPFTableCell> playerCells = staffTableRows.get(0).getTableCells();
        XWPFParagraph p = playerCells.get(22).getParagraphArray(0);
        XWPFRun r = p.createRun();
        setRun(r, "Calibri", 7, licenceNumber, false, false);

    }

    private void processAdditionalFunction(String function, int row) {
        List<XWPFTableCell> playerCells = staffTableRows.get(row).getTableCells();
        for (int i = 22, textIterator = 0; textIterator < function.length() && i <= 31; i++, textIterator++) {
            XWPFParagraph p = playerCells.get(i).getParagraphArray(0);
            XWPFRun r = p.createRun();
            setRun(r, "Calibri", 7, String.valueOf(function.toCharArray()[textIterator]), true, false);
        }
    }

    private static void setRun(XWPFRun run, String fontFamily, int fontSize, String text, boolean bold, boolean addBreak) {
        run.setFontFamily(fontFamily);
        run.setFontSize(fontSize);
        run.setText(text);
        run.setBold(bold);
        if (addBreak) run.addBreak();
    }
}
