package com.example.ginger_alarm;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EmbeddingLayouts
        extends Application  {
    ScenesCollection  scenes;
    Stage window;
    Button button;
    Scene mainScene, alarmsScene, setAlarmScene;

    @Override
    public void start(Stage primaryStage) throws Exception {

       window = primaryStage;
       window.setTitle("Main Window");

        HBox topMenu = new HBox();
        Button buttonA = new Button("File");
        Button buttonB = new Button("Edit");
        Button buttonC = new Button("View");

        topMenu.getChildren().addAll(buttonA, buttonB, buttonC);

        VBox leftMenu = new VBox();
        Button buttonD = new Button("D");
        Button buttonE = new Button("E");
        Button buttonF = new Button("F");

        leftMenu.getChildren().addAll(buttonD, buttonE, buttonF);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topMenu);
        borderPane.setLeft(leftMenu);

        Scene scene = new Scene(borderPane, 200,200);
        window.setScene(scene);
        window.show();
    }

    private void closeProgram() {
        boolean answer;
        answer = ConfirmBox.display("Alert....", "Exit?");

        if (answer){
            System.out.println("File Saved");
            System.out.println("Closing Window");
            window.close();
        }else {
            return;
        }


    }

    public static void main(String[] args) {
            launch(args);
    }

}
