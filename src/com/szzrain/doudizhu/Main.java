package com.szzrain.doudizhu;

import com.szzrain.doudizhu.object.Player;

public class Main {

    private static boolean DEBUG = false;

    public static void main(String[] args) {
        if (args.length!=0){
            DEBUG = true;
            System.out.println("DEBUG MODE IS ON");
        }
        GameRegisterManager.init();
        GameProgressManager.gameStart();



    }

    public static boolean getDebugState(){
        return DEBUG;
    }
}
