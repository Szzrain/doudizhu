package com.szzrain.doudizhu;

import com.szzrain.doudizhu.extend.PlayerBehaviourStupidAI;
import com.szzrain.doudizhu.object.Card;
import com.szzrain.doudizhu.object.CardGroup;
import com.szzrain.doudizhu.object.CardShuffle;
import com.szzrain.doudizhu.object.Player;

import java.util.*;

public class GameProgressManager {
    //TODO: get this from config.json
    private static int holeCardNum = 3;
    private static CardShuffle[] holeCards;
    private static List<CardShuffle> allCards;
    private static Map<String, CardGroup> cardGroupMap;
    private static List<Player> players;

    private static int lastWinner = 0;

    private static Player host;
    private static boolean hostSelected = false;
    private static Player[] hostWilling;

    public static void gameStart(){
        GameRegisterManager.shuffleCards();
        dealCards();
        for (Player p:players){
            showCards(p);
        }
        showHoleCards();
        selectHostTurn();
        showHoleCards();

    }

    private static void gameFinishedClean(){
        host = null;
        hostSelected = false;
        hostWilling = null;
    }

    private static void selectHostTurn(){
        hostWilling = new Player[players.size()];
        System.out.println("Now is chose Host time");
        int currentTurnPlayerIndex = lastWinner;
        System.out.println("Choosing will start with "+players.get(currentTurnPlayerIndex));
        for (int i = 0; i < players.size(); i++) {
            int chose = chooseHost(players.get(currentTurnPlayerIndex));
            if (chose == hostWilling.length-1){
                host = players.get(currentTurnPlayerIndex);
                hostSelected = true;
                System.out.println(players.get(currentTurnPlayerIndex)+" has chosen:"+(chose+1)+" score !");
                announceHost();
                return;
            }else if (chose<0){
                System.out.println(players.get(currentTurnPlayerIndex)+" has give up to elect host !");
            }
            System.out.println(players.get(currentTurnPlayerIndex)+" has chosen:"+(chose+1)+" score");
            if (currentTurnPlayerIndex == players.size()-1){
                currentTurnPlayerIndex = 0;
            }else {
                currentTurnPlayerIndex++;
            }
        }
        for (int i = hostWilling.length-1; i >-1; i--) {
            if(hostWilling[i]!=null){
                host = hostWilling[i];
                announceHost();
                hostSelected = true;
                return;
            }
        }
        System.out.println("No one wants to Become Host!");
        System.out.println("HOW dare are you guys!");
        System.exit(0);
    }

    private static void announceHost(){
        System.out.println("Host is "+host+" !");
    }
    private static int chooseHost(Player p){
        try {
            if (!p.isAI() || Main.getDebugState()){
                System.out.println(p+" will choose...");
                int choose = new Scanner(System.in).nextInt()-1;
                if (choose<0){
                    return -1;
                }else if(hostWilling[choose]!=null){
                    throw new GameProgressException("Option already chosen");
                }else {
                    hostWilling[choose] = p;
                }
                return choose;
            }else {
                int choose = PlayerBehaviourStupidAI.chooseHost();
                hostWilling[choose] = p;
                return choose;
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("ERROR: wrong input or option already chosen, try it again");
            chooseHost(p);
        }
        return -1;
    }

    private static void dealCards(){
        holeCards = new CardShuffle[holeCardNum];
        allCards = GameRegisterManager.getShuffles();
        players = GameRegisterManager.getPlayers();
        cardGroupMap = GameRegisterManager.getCard_table();
        int currentDealPlayerIndex = lastWinner;
        //牌洗过了直接抓
        for (int i = 0; i < allCards.size()-holeCardNum; i++) {
            addPlayerCards(players.get(currentDealPlayerIndex),allCards.get(i));
//          最后一名玩家抓牌后第一名玩家继续抓
            if (currentDealPlayerIndex == players.size()-1){
                currentDealPlayerIndex = 0;
            }else {
                currentDealPlayerIndex++;
            }
        }

        //hole cards
        int holeArrIndex = 0;
        for (int i = allCards.size()-holeCardNum; i < allCards.size(); i++) {
            holeCards[holeArrIndex] = allCards.get(i);
            holeArrIndex++;
        }
    }

    private static void addPlayerCards(Player player, CardShuffle card){
        Map<String,Card> hand = player.getHand();
        //如果card已经创建
        if (hand.containsKey(card.cardId)){
            hand.get(card.cardId).getPrefixCollects().add(card.prefix);
        }else {
            hand.put(card.cardId,new Card(card.mark,cardGroupMap.get(card.cardId)));
            hand.get(card.cardId).getPrefixCollects().add(card.prefix);
        }
    }

    public static void showCards(Player p){
        //for convenience, in debug mode will show all player's cards
        if(Main.getDebugState()){
            System.out.print(p.getPlayerName()+" Card:");
            soutCards(p);
            //not debug mod, AI cards only show count, player cards shown with details
        }else {
            //is AI, only show count
            if (p.isAI()){
                int cardCount = 0;
                for (Card card:p.getHand().values()){
                    for (String t:card.getPrefixCollects()){
                        cardCount++;
                    }
                }
                System.out.println(p.getPlayerName()+":"+cardCount+" cards left");
                //is player, show card details
            }else {
                System.out.print("Your Card:");
                soutCards(p);
            }
        }
    }

    private static void showHoleCards(){
        if (Main.getDebugState() || hostSelected){
            System.out.print("Hole Cards:");
            for(CardShuffle cardShuffle:holeCards){
                System.out.print(" "+cardShuffle);
            }
            System.out.println();
        }
    }

    private static void soutCards(Player p) {
        List<CardShuffle> presentTemp = new ArrayList<>();
        for(Card card:p.getHand().values()){
            for (String t:card.getPrefixCollects()){
                presentTemp.add(new CardShuffle(t,card.getCardGroup().getCardGroupId(),card.getMark()));
            }
//            System.out.print(card+",");
        }
        Collections.sort(presentTemp);
        for (CardShuffle c:presentTemp){
            System.out.print(c+" ");
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
