package com.szzrain.doudizhu.object;

import java.util.LinkedHashMap;
import java.util.Map;

public class Player {

    private static int playerCount = 0;
    private Map<String,Card> hand;
    private final String playerName;
    private final boolean isAI;
    private final Map<String,CardShuffle> shuffleHand;


    public Player(boolean isAI) {
        this.hand = new LinkedHashMap<>();
        this.shuffleHand = new LinkedHashMap<>();
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

    public Map<String, CardShuffle> getShuffleHand() {
        return shuffleHand;
    }

    public void resetPlayer(){
        this.hand = new LinkedHashMap<>();
    }

    @Override
    public String toString() {
        return getPlayerName();
    }
}
