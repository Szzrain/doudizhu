package com.szzrain.doudizhu.object;

import com.alibaba.fastjson.annotation.JSONField;

public class Deck {

    @JSONField(name = "deckId")
    private String deckId;

    //0表示牌本身，1表示大一个level，以此类推，*表示任何牌
    //"0","00","000*","01234"...
    @JSONField(name = "deckFormat")
    private String deckFormat;

    //表示能够管哪些deck
    //{"deckId","deckId2"...}
    @JSONField(name = "targetDeck")
    private String[] targetDeck;

    @JSONField(name = "isBomb")
    private boolean isBomb;

    @JSONField(name = "bombTarget")
    private String[] bombTarget;

    public Deck() {
    }

    public Deck(String deckId, String deckFormat, String[] targetDeck, boolean isBomb, String[] bombTarget) {
        this.deckId = deckId;
        this.deckFormat = deckFormat;
        this.targetDeck = targetDeck;
        this.isBomb = isBomb;
        this.bombTarget = bombTarget;
    }

    public String getDeckId() {
        return deckId;
    }

    public String getDeckFormat() {
        return deckFormat;
    }

    public String[] getTargetDeck() {
        return targetDeck;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public String[] getBombTarget() {
        return bombTarget;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }

    public void setDeckFormat(String deckFormat) {
        this.deckFormat = deckFormat;
    }

    public void setTargetDeck(String[] targetDeck) {
        this.targetDeck = targetDeck;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public void setBombTarget(String[] bombTarget) {
        this.bombTarget = bombTarget;
    }
}
