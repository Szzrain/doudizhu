package com.szzrain.doudizhu.object;

public class CardShuffle {
    public String prefix;
    public String cardId;
    public String mark;

    public CardShuffle(String prefix, String cardId,String mark) {
        this.prefix = prefix;
        this.cardId = cardId;
        this.mark = mark;
    }

    @Override
    public String toString() {
        return prefix+mark;
    }
}
