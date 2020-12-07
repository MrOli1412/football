package com.pl.football.backend.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebsiteParesServiceTest {
    /*
    data-zpn-id
    data-zpn-name
     */
    Map<String, String> listOfStates = new HashMap<>();
    Map<String, String> listOfLeagues = new HashMap<>();


    @Before()
    public void parseStates() throws IOException {
//        Map<String, String> listOfStates = new HashMap<>();
        Document site = Jsoup.connect("https://www.laczynaspilka.pl/rozgrywki/nizsze-ligi.html").get();
        site.getAllElements();
        Elements listOfStatesElement = site.getElementsByAttribute("data-zpn-id");
        listOfStatesElement.forEach(state -> {
            listOfStates.put(state.attr("data-zpn-id"), state.attr("data-zpn-name"));
        });
    }

    /*
    data-game-id
     */
    @Test
    public void getListOfLeagueFromState() {

        String season = "2020/2021";
        listOfStates.forEach((key, value) -> {
            try {
                Document site = Jsoup.connect("https://www.laczynaspilka.pl/league/get_lower?&zpn_id[]=" + Integer.parseInt(key) + "&mode=league&season=" + season + "&juniors=").get();
                Elements elementsByAttribute = site.getElementsByAttribute("data-game-id");
                elementsByAttribute.forEach(element -> {
                    if (element instanceof Element) {
                        String name = ((TextNode) element.childNode(0)).text();
                        if (name.contains("<")) {
                            listOfLeagues.put(element.attr("data-game-id"), name.substring(0, name.indexOf("<")));
                        }else{
                            name = ((TextNode)element.childNode(1).childNode(0)).text().replace("\n","");
                            listOfLeagues.put(element.attr("data-game-id"), name);
                        }
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
            Assert.assertTrue("Lista lig większa od 0" + listOfLeagues.size(), !listOfLeagues.isEmpty());
        });
    }


}
