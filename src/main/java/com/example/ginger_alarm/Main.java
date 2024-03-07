package com.example.ginger_alarm;

import javafx.application.Application;
import javafx.concurrent.ScheduledService;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    Stage mainWindow, alarmPopUpWindowStage;
    Button alarmsButton,newAlarmButton, exitAppButton;
    VBox  newAlarmLayout, alarmsLayout;

    @Override
    public void start(Stage stage) throws Exception {
        BackgroundHandler backgroundHandler= new BackgroundHandler();
        mainWindow = new Stage(StageStyle.TRANSPARENT);
        alarmPopUpWindowStage = new Stage(StageStyle.TRANSPARENT);
        alarmPopUpWindowStage.setTitle("Alarm Alert");

        backgroundHandler.handleBackgroundScene(mainWindow);

        mainWindow.setTitle("Ginger Alarms");
        mainWindow.setOpacity(1.0);
        mainWindow.initStyle(StageStyle.TRANSPARENT);
        // left nav Buttons
        alarmsButton = new Button();
        alarmsButton.setText("Alarms");
        alarmsButton.getStyleClass().add("labelText");
        newAlarmButton = new Button();
        newAlarmButton.setText("New Alarm");
        newAlarmButton.getStyleClass().add("labelText");
        exitAppButton = new Button();
        exitAppButton.setText("Exit App");

        alarmsButton.getStyleClass().add("mainButton");
        newAlarmButton.getStyleClass().add("mainButton");
        exitAppButton.getStyleClass().add("mainButton");
        exitAppButton.setId("exitButton");
        exitAppButton.getStyleClass().add("labelText");
        newAlarmButton.setAlignment(Pos.BASELINE_LEFT);
        VBox verticalLayout = new VBox();
        verticalLayout.getChildren().addAll(alarmsButton,newAlarmButton, exitAppButton);
        verticalLayout.setSpacing(10);
        verticalLayout.setAlignment(Pos.CENTER);
        // Home Scene
        StackPane contentLayout = new StackPane();
        // home Layout
        Layouts layouts = new Layouts();
        alarmsLayout = layouts.alarmsLayout();
        newAlarmLayout = layouts.newAlarmLayout();
        // Alarms Layout
        // Append Layout
        contentLayout.getChildren().add(alarmsLayout);
        HBox mainHorizontalLayout = new HBox();
        mainHorizontalLayout.getChildren().addAll(verticalLayout, contentLayout);
        // Adding Buttons to grid
        mainHorizontalLayout.getStyleClass().add("main-hbox");
        Scene scene = new Scene(mainHorizontalLayout);
        scene.setFill(Color.TRANSPARENT);
        //  Getting the stylesheet file
        scene.getStylesheets().add("file:/samba/public/documents/github/ginger_alarm/src/main/resources/style/main.css");
        mainWindow.setScene(scene);
        // Handle navigation clicks
        alarmsButton.setOnAction(e->{
            contentLayout.getChildren().removeFirst();
            contentLayout.getChildren().add(alarmsLayout);
        });
        newAlarmButton.setOnAction(e->{
            contentLayout.getChildren().removeFirst();
            contentLayout.getChildren().add(newAlarmLayout);
        });
        exitAppButton.setOnAction(e->{
            // mainWindow.close();
            //  mainWindow.setOnCloseRequest(event -> {
            // Optionally prompt the user for confirmation or perform cleanup tasks
            // Hide the primary stage instead of closing it
            mainWindow.hide();
            e.consume();

        });

        BackgroundTask BackgroundTaskHandler = new BackgroundTask();


        ScheduledService<Void> backgroundService = BackgroundTaskHandler.getBackgroundService(alarmPopUpWindowStage);
        backgroundService.start();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
