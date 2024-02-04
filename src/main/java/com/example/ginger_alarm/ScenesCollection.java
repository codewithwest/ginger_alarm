package com.example.ginger_alarm;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScenesCollection {

    Scene mainScene, alarmsScene, setAlarmScene;
    public Scene mainSceneCreator(Stage window,Scene goToMainScene){

        Button goToAlarmScene = new Button("Go To Alarms");
        goToAlarmScene.setOnAction(e -> window.setScene(goToMainScene));
        StackPane mainLayout = new StackPane();
        mainLayout.getChildren().addAll(goToAlarmScene);
        mainScene = new Scene(mainLayout, 600, 500);
        return mainScene;
    }
    public Scene alarmsSceneCreator(Stage window,Scene goToMainScene){

        Button goToMain = new Button("Go Home");
        goToMain.setOnAction(e -> window.setScene(goToMainScene));
        VBox alarmsLayout = new VBox(20);
        alarmsLayout.getChildren().addAll(goToMain);
        alarmsScene = new Scene(alarmsLayout, 600, 500);
        return alarmsScene;
    }
    public Scene setAlarmSceneCreator(Stage window,Scene goToMainScene){

        Button goToMain = new Button("Go Home");
        goToMain.setOnAction(e -> window.setScene(goToMainScene));
        VBox alarmsLayout = new VBox(20);
        alarmsLayout.getChildren().addAll(goToMain);
        setAlarmScene = new Scene(alarmsLayout, 600, 500);
        return setAlarmScene;
    }

}
