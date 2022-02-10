package com.szzrain.doudizhu.object;

public class CardShuffle {
    public String prefix;
    public String cardId;

    public CardShuffle(String prefix, String cardId) {
        this.prefix = prefix;
        this.cardId = cardId;
    }

    @Override
    public String toString() {
        return prefix+cardId;
    }
}
