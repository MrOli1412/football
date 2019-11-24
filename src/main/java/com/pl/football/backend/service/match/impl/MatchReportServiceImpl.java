package com.pl.football.backend.service.match.impl;

import com.pl.football.backend.model.Match;
import com.pl.football.backend.service.match.MatchReportService;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

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

    @Override
    public void convertFile(UUID matchId) throws IOException {
        File msWordPath = new ClassPathResource("/static/spr.docx").getFile();
        XWPFDocument document = new XWPFDocument(new FileInputStream(msWordPath));
        List<XWPFParagraph> paragraphs = document.getParagraphs();
        List<IBodyElement> bodyElements = document.getBodyElements();
        List<XWPFTable> tables = document.getTables();
        XWPFTable zawodnicy = tables.get(0);
        XWPFTable obsluga = tables.get(1);

        setMatchData(paragraphs.get(1), new Match());
        setPlayers(tables.get(0), new Match());
        FileOutputStream out = new FileOutputStream("spr2.docx");
        document.write(out);
        out.close();
        document.close();
    }

    private XWPFParagraph setMatchData(XWPFParagraph paragraph, Match match) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String format = dateTimeFormatter.format(now);
        String text = paragraph.getText();
        String[] split = text.split(":");
        split[0] = split[0] + ": LKS PAWŁÓW ";
        split[1] = split[1] + ": CZARNE ";
        split[2] = split[2] + ": " + format;
        for (int i = paragraph.getRuns().size() - 1; i >= 0; i--) {
            paragraph.removeRun(i);
        }
//        XWPFRun run = paragraph.createRun();
        setRun(paragraph.createRun(), "Calibri", 9, split[0] + split[1] + split[2], true, false);
        return paragraph;
    }

    private XWPFTable setPlayers(XWPFTable table, Match match) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("ddMMyy");
        char[] format = dateTimeFormatter.format(now).toCharArray();
        char[] player = "PELCZAR BLAZEJ".toCharArray();
        List<XWPFTableRow> rows = table.getRows();
        rows.forEach(row -> {
            if (row.getTableCells().size() == 33) {
                List<XWPFTableCell> playerCells = row.getTableCells();
                XWPFParagraph addParagraph = playerCells.get(0).getParagraphArray(0);
                XWPFParagraph addParagraph2 = playerCells.get(1).getParagraphArray(0);
                XWPFRun run = addParagraph.createRun();
                XWPFRun run2 = addParagraph2.createRun();
                setRun(run, "Calibri", 8, "K", true, false);
                setRun(run2, "Calibri", 8, "14", false, false);
                for (int i = 2, textIterator = 0; textIterator < player.length && i <= 23; i++, textIterator++) {
                    XWPFParagraph p = playerCells.get(i).getParagraphArray(0);
                    XWPFRun r = p.createRun();
                    setRun(r, "Calibri", 8, String.valueOf(player[textIterator]), false, false);
                }
                for (int i = 25, textIterator = 0; textIterator < format.length && i <= 30; i++, textIterator++) {
                    XWPFParagraph p = playerCells.get(i).getParagraphArray(0);
                    XWPFRun r = p.createRun();
                    setRun(r, "Calibri", 8, String.valueOf(format[textIterator]), false, false);
                }
            }
        });
        return table;
    }

    private XWPFTable setStaff(XWPFTable table, Match match) {
        char[] staff = "PELCZAR BLAZEJ".toCharArray();
        List<XWPFTableRow> rows = table.getRows();

        rows.forEach(row -> {
            if (row.getTableCells().size() == 33) {

                List<XWPFTableCell> staffCells = row.getTableCells();

                XWPFParagraph addParagraph = staffCells.get(0).getParagraphArray(0);
                XWPFRun run = addParagraph.createRun();
                setRun(run, "Calibri", 7, "K", true, false);


                for (int i = 1, textIterator = 0; textIterator < staff.length && i <= 20; i++, textIterator++) {
                    XWPFParagraph p = staffCells.get(i).getParagraphArray(0);
                    XWPFRun r = p.createRun();
                    setRun(r, "Calibri", 8, String.valueOf(staff[textIterator]), false, false);
                }
            }
        });
        return table;
    }

    private static void setRun(XWPFRun run, String fontFamily, int fontSize, String text, boolean bold, boolean addBreak) {
        run.setFontFamily(fontFamily);
        run.setFontSize(fontSize);
        run.setText(text);
        run.setBold(bold);
        if (addBreak) run.addBreak();
    }
}
