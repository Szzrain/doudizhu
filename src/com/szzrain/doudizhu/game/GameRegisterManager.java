package com.szzrain.doudizhu.game;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.szzrain.doudizhu.IOTool;
import com.szzrain.doudizhu.Main;
import com.szzrain.doudizhu.game.GameProgressException;
import com.szzrain.doudizhu.object.*;

import java.util.*;

public class GameRegisterManager {
    private static Map<String,Deck> deck_table;
    private static Map<String,CardGroup> card_table;
    private static List<Player> players;
    private static List<CardShuffle> shuffles;


    private static boolean isInit = true;

    public static void init(){
        if (isInit || Main.getDebugState()){
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


            players.add(new Player(false));
            players.add(new Player(true));
            players.add(new Player(true));

            isInit = false;
        }else {
            throw new GameProgressException("Game Already Initiated");
        }
    }

    public static void shuffleCards(){
        shuffles = new ArrayList();
        for (Player p:players){
            p.resetPlayer();
            for (String id:card_table.keySet()){
                p.getHand().put(id,new Card(card_table.get(id).getMark(),card_table.get(id)));
            }
        }
        for(CardGroup c:card_table.values()){
            for (String s: c.getPossiblePrefix()) {
                shuffles.add(new CardShuffle(s,c.getCardGroupId(),c.getMark()));
            }
        }
        Collections.shuffle(shuffles);
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

    public static List<CardShuffle> getShuffles() {
        return shuffles;
    }
}
