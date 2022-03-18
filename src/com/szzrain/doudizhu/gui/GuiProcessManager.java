package com.szzrain.doudizhu.gui;

import com.szzrain.doudizhu.LOGGER;
import com.szzrain.doudizhu.object.CardShuffle;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GuiProcessManager {
    private static boolean init = false;
    private static MainController mainController;

    private static final Lock buttonLock;
    private static final Condition buttonCondition;
    static {
        buttonLock = new ReentrantLock();
        buttonCondition = buttonLock.newCondition();
    }

    private static int hostResult = 0;

    public static boolean isInit() {
        return init;
    }

    public static void setMainController(MainController mainController){
        GuiProcessManager.mainController = mainController;
        LOGGER.info("MainController received");
        init = true;
    }

    public static Lock getButtonLock(){
        return buttonLock;
    }

    public static void refreshPlayerHands(){

    }

    public static void setHoleCard(CardShuffle[] card){
        mainController.setHoleCard(card);
    }

    public static Condition getButtonCondition(){
        return buttonCondition;
    }

    public static void setHostResult(int num){
        hostResult = num;
    }

    public static int getChooseHostResult(){
        hostResult = 0;
        mainController.setHostSelectButtonVisibility(true);
        try {
            buttonLock.lock();
            buttonCondition.await();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            buttonLock.unlock();
        }
        return hostResult;
    }
}
