package com.szzrain.doudizhu.object;

import com.alibaba.fastjson.annotation.JSONField;

/*
牌面大小
可形成的组合 arr

 */
public class CardGroup {

    //牌面（显示值）
    @JSONField(name = "mark")
    private String mark;

    //牌值（调用值）
    @JSONField(name = "level")
    private int level;

    //可能的前缀（花色）
    @JSONField(name = "possiblePrefix")
    private String[] possiblePrefix;

    //可能的组合（出牌方式）
    @JSONField(name = "possibleDeck")
    private String[] possibleDecks;

    @JSONField(name = "cardGroupId")
    private String cardGroupId;

    @JSONField(name = "isAny")
    private boolean isAny;

    public CardGroup() {
    }

    public CardGroup(String mark, int level, String[] possiblePrefix, String[] possibleDecks, String cardGroupId, boolean isAny) {
        this.mark = mark;
        this.level = level;
        this.possiblePrefix = possiblePrefix;
        this.possibleDecks = possibleDecks;
        this.cardGroupId = cardGroupId;
        this.isAny = isAny;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPossiblePrefix(String[] possiblePrefix) {
        this.possiblePrefix = possiblePrefix;
    }

    public void setPossibleDecks(String[] possibleDecks) {
        this.possibleDecks = possibleDecks;
    }

    public void setCardGroupId(String cardGroupId) {
        this.cardGroupId = cardGroupId;
    }

    public void setAny(boolean any) {
        isAny = any;
    }

    public String getMark() {
        return mark;
    }

    public int getLevel() {
        return level;
    }

    public String[] getPossiblePrefix() {
        return possiblePrefix;
    }

    public String[] getPossibleDecks() {
        return possibleDecks;
    }

    public String getCardGroupId() {
        return cardGroupId;
    }

    public boolean isAny() {
        return isAny;
    }
}
