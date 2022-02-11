package com.szzrain.doudizhu.object;

import com.szzrain.doudizhu.GameRegisterManager;

public class CardShuffle implements Comparable{
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

    @Override
    public int compareTo(Object o) {
        return GameRegisterManager.getCard_table().get(this.cardId).getLevel() - GameRegisterManager.getCard_table().get(((CardShuffle)o).cardId).getLevel();
    }
}
