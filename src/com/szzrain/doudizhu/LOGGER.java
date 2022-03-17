package com.szzrain.doudizhu;

import com.szzrain.doudizhu.gui.GuiProcessManager;

public class LOGGER {
    public static void info(Object info){
        System.out.println(info);
    }

    public static void info(){
        System.out.println();
    }

    public static void info_no_return(Object info){
        System.out.print(info);
    }
}
