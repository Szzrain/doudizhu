package com.szzrain.doudizhu.object;

import java.util.ArrayList;

public class Card {
    private final String mark;
    private final CardGroup cardGroup;

    //存储花色，表示相同的牌
    private final ArrayList<String> prefixCollects;

    public Card(String mark, CardGroup cardGroup) {
        this.mark = mark;
        this.cardGroup = cardGroup;
        prefixCollects = new ArrayList<>();
    }

    public String getMark() {
        return mark;
    }

    public CardGroup getCardGroup() {
        return cardGroup;
    }

    public ArrayList<String> getPrefixCollects() {
        return prefixCollects;
    }

    @Override
    public String toString() {
        return getPrefixCollects()+getMark();
    }
}
