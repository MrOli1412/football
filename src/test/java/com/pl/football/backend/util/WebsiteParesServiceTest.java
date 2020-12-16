package com.pl.football.backend.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebsiteParesServiceTest {
    /*
    data-zpn-id
    data-zpn-name
     */
    Map<String, String> listOfStates = new HashMap<>();
    Map<String, Map<String, String>> listOfLeagues = new HashMap<>();
    Map<String, Map<String, String>> listOfTeams = new HashMap<>();


    @Before()
    public void parseStates() throws IOException {
//        Map<String, String> listOfStates = new HashMap<>();
        Document site = Jsoup.connect("https://www.laczynaspilka.pl/rozgrywki/nizsze-ligi.html").get();
        site.getAllElements();
        Elements listOfStatesElement = site.getElementsByAttribute("data-zpn-id");
        listOfStatesElement.forEach(state -> {
            listOfStates.put(state.attr("data-zpn-id"), state.attr("data-zpn-name"));
        });
        getListOfLeagueFromState();
    }

    /*
    data-game-id
     */

    public void getListOfLeagueFromState() {

        String season = "2020/2021";
        listOfStates.forEach((key, value) -> {
            try {
                Map<String, String> leagues = new HashMap<>();
//                Document site = Jsoup.parse("https://www.laczynaspilka.pl/league/get_lower?&zpn_id[]=" + Integer.parseInt(key) + "&mode=league&season=" + season + "&juniors=").get();
                String url = "https://www.laczynaspilka.pl/league/get_lower?&zpn_id[]=" + Integer.parseInt(key) + "&mode=league&season=" + season + "&juniors=";
                Document site = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
                Elements elementsByAttribute = site.getElementsByAttribute("data-game-id");
                elementsByAttribute.forEach(element -> {
                    if (element instanceof Element) {
                        String name = ((TextNode) element.childNode(0)).text();
                        if (name.contains("<")) {
                            leagues.put(element.attr("data-game-id"), new String(name.substring(0, name.indexOf("<")).getBytes(StandardCharsets.UTF_8)));
                        }
                    }
                });
                listOfLeagues.put(key, leagues);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Assert.assertTrue("Lista lig wi?ksza od 0" + listOfLeagues.size(), listOfLeagues.size() > 15);


    }

    @Test
    public void getTeamsForLeague() {
        listOfLeagues.forEach((regionId, mapOfLeagues) -> mapOfLeagues.forEach((leagueId, leagueName) -> {
            Map<String, String> teams = new HashMap<>();
            String url = "https://www.laczynaspilka.pl/druzyny/nizsze-ligi," + Integer.parseInt(leagueId) + ".html";
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
                    teams.put(href, teamName);
                });
                throw new Exception("|break");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
    }


}
