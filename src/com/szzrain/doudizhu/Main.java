package com.szzrain.doudizhu;

import com.szzrain.doudizhu.game.GameProgressManager;
import com.szzrain.doudizhu.game.GameRegisterManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    private static boolean DEBUG = false;

    public static void main(String[] args) {
        if (args.length!=0){
            DEBUG = true;
            LOGGER.info("DEBUG MODE IS ON");
        }
        GameRegisterManager.init();
        launch(args);
        System.exit(0);
    }

    public static boolean getDebugState(){
        return DEBUG;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Main.fxml")));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        new Thread(new gameThread()).start();
    }
}
class gameThread implements Runnable{
    @Override
    public void run() {
        GameProgressManager.gameStart();
    }
}