package com.szzrain.doudizhu;

import com.szzrain.doudizhu.object.Player;

public class Main {

public static final boolean DEBUG = false;

    public static void main(String[] args) {
        GameRegisterManager.init();
        GameProgressManager.gameStart();


        for (Player p:GameRegisterManager.getPlayers()){
            GameProgressManager.showCards(p);
        }
    }

}
