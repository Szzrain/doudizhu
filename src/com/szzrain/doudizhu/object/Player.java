package com.szzrain.doudizhu.object;

import java.util.LinkedHashMap;
import java.util.Map;

public class Player {

    private static int playerCount = 0;
    private Map<String,Card> hand;
    private final String playerName;
    private final boolean isAI;

    public Player(boolean isAI) {
        this.hand = new LinkedHashMap<>();
        this.isAI = isAI;
        playerCount++;
        playerName = "player" + playerCount;
    }

    public Map<String, Card> getHand() {
        return hand;
    }

    public boolean isAI() {
        return isAI;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void resetPlayer(){
        this.hand = new LinkedHashMap<>();
    }
}
