package com.pl.football.backend.util;

import com.pl.football.backend.model.pzpn.League;
import com.pl.football.backend.model.pzpn.PzpnTeam;
import com.pl.football.backend.model.pzpn.State;
import com.pl.football.backend.repository.pzpn.LeagueRepository;
import com.pl.football.backend.repository.pzpn.PZPNTeamRepository;
import com.pl.football.backend.repository.pzpn.StateRepository;
import io.swagger.models.auth.In;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class WebsiteParserService {
    private final LeagueRepository leagueRepository;
    private final StateRepository stateRepository;
    private final PZPNTeamRepository pzpnTeamRepository;

    public WebsiteParserService(LeagueRepository leagueRepository, StateRepository stateRepository, PZPNTeamRepository pzpnTeamRepository) {
        this.leagueRepository = leagueRepository;
        this.stateRepository = stateRepository;
        this.pzpnTeamRepository = pzpnTeamRepository;
    }


    @Transactional
    public void parseStates() throws IOException {
        List<State> listOfStates = new ArrayList<>();
        Document site = Jsoup.connect("https://www.laczynaspilka.pl/rozgrywki/nizsze-ligi.html").get();
        site.getAllElements();
        Elements listOfStatesElement = site.getElementsByAttribute("data-zpn-id");
        listOfStatesElement.forEach(state -> {
            listOfStates.add(new State(Integer.parseInt(state.attr("data-zpn-id")), state.attr("data-zpn-name")));
        });
        stateRepository.saveAll(listOfStates);

//        getListOfLeagueFromState();
    }

    /*
    data-game-id
     */
    @Transactional
    public void prepareLeagues() {

        String season = "2020/2021";
        List<State> all = stateRepository.findAll();
        List<League> leagues = new ArrayList<>();

        all.forEach((key) -> {
            try {
//                Document site = Jsoup.parse("https://www.laczynaspilka.pl/league/get_lower?&zpn_id[]=" + Integer.parseInt(key) + "&mode=league&season=" + season + "&juniors=").get();
                String url = "https://www.laczynaspilka.pl/league/get_lower?&zpn_id[]=" + key.getId() + "&mode=league&season=" + season + "&juniors=";
                Document site = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
                Elements elementsByAttribute = site.getElementsByAttribute("data-game-id");
                elementsByAttribute.forEach(element -> {
                    if (element instanceof Element) {
                        String name = ((TextNode) element.childNode(0)).text();
                        if (name.contains("<")) {
                            leagues.add(new League(Integer.parseInt(element.attr("data-game-id")),
                                    new String(name.substring(0, name.indexOf("<")).getBytes(StandardCharsets.UTF_8)),
                                    key
                            ));
                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                leagueRepository.saveAll(leagues);
            }
        });


    }
    @Transactional
    public void prepareTeams() {
        List<League> leagues = leagueRepository.findAll();
        leagues.forEach(league -> {
            List<PzpnTeam> teams = new ArrayList<>();
            String url = "https://www.laczynaspilka.pl/druzyny/nizsze-ligi," + league.getId() + ".html";
            try {
                Document site = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
                Elements elementsByAttribute = site.select("div.row > a");
                elementsByAttribute.forEach(element -> {
                    String href = element.attr("href");
                    AtomicReference<String> name = null;
                    String teamName = element.getElementsByClass("name").get(0).text();
//                    element.childNodes().forEach(node -> {
//                       Element el = (Element) node;
//                       el.getElementsByClass("name");
//                    });
                    teams.add(new PzpnTeam(href,
                            teamName,
                            league));
                });
                throw new Exception("|break");
            } catch (IOException e) {
//                e.printStackTrace();
            } catch (Exception e) {
                System.out.println(league.getLeagueName() + " " + teams.size());

//                e.printStackTrace();
            } finally {
                pzpnTeamRepository.saveAll(teams);
            }
        });

    }
}
