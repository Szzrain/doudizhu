package com.szzrain.doudizhu;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.szzrain.doudizhu.object.*;

import java.util.*;

public class GameRegisterManager {
    private static Map<String,Deck> deck_table;
    private static Map<String,CardGroup> card_table;
    private static List<Player> players;
    private static List<CardShuffle> shuffles;


    private static boolean isInit = true;

    public static void init(){
        if (isInit || Main.DEBUG){
            deck_table = new HashMap<>();
            card_table = new HashMap<>();
            players = new ArrayList<>();


            JSONArray cardGroupArr = JSONArray.parseArray(IOTool.readJsonFile(String.valueOf(Main.class.getResource("/cardGroups.json")).substring(6)));
            for (Object c :
                    cardGroupArr) {
                CardGroup temp = JSONObject.parseObject(c.toString(),CardGroup.class);
                card_table.put(temp.getCardGroupId(),temp);
            }


            JSONArray deckArr = JSONArray.parseArray(IOTool.readJsonFile(String.valueOf(Main.class.getResource("/Decks.json")).substring(6)));
            for (Object d :
                    deckArr) {
                Deck temp = JSONObject.parseObject(d.toString(),Deck.class);
                deck_table.put(temp.getDeckId(),temp);
            }


            players.add(new Player(true));
            players.add(new Player(true));
            players.add(new Player(false));

            isInit = false;
        }
    }

    public static void shuffleCards(){
        shuffles = new ArrayList();
        for (Player p:players){
            p.resetPlayer();
            for (String s:card_table.keySet()){
                p.getHand().put(s,new Card(card_table.get(s).getMark(),card_table.get(s)));
            }
        }
        for(CardGroup c:card_table.values()){
            for (String s: c.getPossiblePrefix()) {
                shuffles.add(new CardShuffle(s,c.getCardGroupId()));
            }
        }
        Collections.shuffle(shuffles);
        System.out.println(shuffles);
    }


    public static Map<String, Deck> getDeck_table() {
        return deck_table;
    }

    public static Map<String, CardGroup> getCard_table() {
        return card_table;
    }

    public static List<Player> getPlayers() {
        return players;
    }
}
