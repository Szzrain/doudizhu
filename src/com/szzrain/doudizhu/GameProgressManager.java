package com.szzrain.doudizhu;

import com.szzrain.doudizhu.object.Card;
import com.szzrain.doudizhu.object.CardGroup;
import com.szzrain.doudizhu.object.CardShuffle;
import com.szzrain.doudizhu.object.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameProgressManager {
    private static int holeCardNum = 3;
    private static CardShuffle[] holeCards;
    private static List<CardShuffle> allCards;
    private static Map<String, CardGroup> cardGroupMap;
    private static List<Player> players;
    private static int lastWinner = 0;


    public static void gameStart(){
        GameRegisterManager.shuffleCards();
        dealCards();
        System.out.println(GameRegisterManager.getShuffles());
    }

    //TODO:把添加牌的逻辑拆成单独的方法
    public static void dealCards(){
        holeCards = new CardShuffle[holeCardNum];
        allCards = GameRegisterManager.getShuffles();
        players = GameRegisterManager.getPlayers();
        cardGroupMap = GameRegisterManager.getCard_table();
        int currentDealPlayerIndex = lastWinner;
        //牌洗过了直接抓
        for (int i = 0; i < allCards.size()-holeCardNum; i++) {
            Map<String,Card> hand = players.get(currentDealPlayerIndex).getHand();
            if(hand.containsKey(allCards.get(i).cardId)){
                hand.get(allCards.get(i).cardId).getPrefixCollects().add(allCards.get(i).prefix);
            }else {
                hand.put(allCards.get(i).cardId,new Card(allCards.get(i).mark,cardGroupMap.get(allCards.get(i).cardId)));
                hand.get(allCards.get(i).cardId).getPrefixCollects().add(allCards.get(i).prefix);
            }
//          最后一名玩家抓牌后第一名玩家继续抓
            if (currentDealPlayerIndex == players.size()-1){
                currentDealPlayerIndex = 0;
            }else {
                currentDealPlayerIndex++;
            }
        }
    }

    public static void showCards(Player p){
        if(Main.DEBUG){
            System.out.print(p.getPlayerName()+" Card:");
            soutCards(p);
            //not debug mod, AI cards only show count, player cards shown with details
        }else {
            if (p.isAI()){
                int cardCount = 0;
                for (Card card:p.getHand().values()){
                    for (String t:card.getPrefixCollects()){
                        cardCount++;
                    }
                }
                System.out.println(p.getPlayerName()+":"+cardCount+" cards left");
            }else {
                System.out.print("Your Card:");
                soutCards(p);
            }
        }
    }


    //TODO:排除掉没有的牌
    private static void soutCards(Player p) {
        List<String> presentTemp = new ArrayList<>();
        for(Card card:p.getHand().values()){

            System.out.print(card+",");
        }
        int cardCount = 0;
        for (Card card:p.getHand().values()){
            for (String t:card.getPrefixCollects()){
                cardCount++;
            }
        }
        System.out.println("   Total"+cardCount+" Cards");
    }


}
