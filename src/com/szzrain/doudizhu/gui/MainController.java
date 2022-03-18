package com.szzrain.doudizhu.gui;

import com.szzrain.doudizhu.object.CardShuffle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public HBox playerMain;
    public HBox holeCardBox;
    public VBox leftPlayer;
    public VBox rightPlayer;
    public AnchorPane rootPane;
    public TextField info;

    public Button host_one_score;
    public Button host_two_score;
    public Button host_three_score;
    public Button passHost;

    public void setHostSelectButtonVisibility(boolean isVisible){
        host_one_score.setVisible(isVisible);
        host_two_score.setVisible(isVisible);
        host_three_score.setVisible(isVisible);
        passHost.setVisible(isVisible);
    }


    public void setHoleCard(CardShuffle[] card){
        holeCardBox.getChildren().add(new Button("16"));
    }

    public void hostButtonClick(ActionEvent event){
        if (event.getSource() instanceof Button){
            setHostSelectButtonVisibility(false);
            Button t = (Button) event.getSource();
            GuiProcessManager.setHostResult(Integer.parseInt(t.getId()));
            GuiProcessManager.getButtonLock().lock();
            GuiProcessManager.getButtonCondition().signal();
            GuiProcessManager.getButtonLock().unlock();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GuiProcessManager.setMainController(this);
    }
}
