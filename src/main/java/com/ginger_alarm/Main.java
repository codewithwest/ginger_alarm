package com.ginger_alarm;

import com.ginger_alarm.backend.BackgroundTaskHandler;
import com.ginger_alarm.frontend.components.BackgroundHandler;
import com.ginger_alarm.frontend.components.Layouts;
import com.ginger_alarm.reusables.ReusableComponents;
import javafx.application.Application;
import javafx.concurrent.ScheduledService;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.List;

public class Main extends Application {
    Stage mainWindow, alarmPopUpWindowStage;
    Button alarmsButton, newAlarmButton, settingsButton, exitAppButton;
    VBox newAlarmLayout, alarmsLayout, settingsLayout;
    String stylesBaseLocal = "file:/samba/public/documents/github/ginger_alarm/src/main/resources/style/";

    @Override
    public void start(Stage stage) throws Exception {
        BackgroundHandler backgroundHandler = new BackgroundHandler();
        mainWindow = new Stage(StageStyle.TRANSPARENT);
        alarmPopUpWindowStage = new Stage(StageStyle.TRANSPARENT);
        alarmPopUpWindowStage.setTitle("Alarm Alert");

        ReusableComponents reusableComponents = new ReusableComponents();
        backgroundHandler.handleBackgroundScene(mainWindow);

        mainWindow.setTitle("Ginger Alarms");
        mainWindow.setOpacity(1.0);
        mainWindow.initStyle(StageStyle.TRANSPARENT);
        // Navigation Buttons
        alarmsButton = reusableComponents.newBasicButton("Alarms", List.of("labelText", "mainButton"));
        newAlarmButton = reusableComponents.newBasicButton("New", List.of("labelText", "mainButton"));
        settingsButton = reusableComponents.newBasicButton("Config", List.of("labelText", "mainButton"));
        exitAppButton = reusableComponents.newBasicButton("Close", List.of("labelText", "mainButton"));

        exitAppButton.setId("exitButton");
//        newAlarmButton.setAlignment(Pos.BASELINE_LEFT);
        VBox verticalLayout = new VBox();
        verticalLayout.getChildren().addAll(alarmsButton, newAlarmButton, settingsButton, exitAppButton);
        verticalLayout.getStyleClass().add("main-btns-cont");
        // Home Scene
        StackPane contentLayout = new StackPane();
        // home Layout
        Layouts layouts = new Layouts();
        alarmsLayout = layouts.alarmsLayout();
        newAlarmLayout = layouts.newAlarmLayout();
        settingsLayout = layouts.settingsLayout();
        // Alarms Layout
        // Append Layout
        contentLayout.getChildren().add(alarmsLayout);
        contentLayout.getStyleClass().add("main-content-cont");
        HBox mainHorizontalLayout = new HBox();
        mainHorizontalLayout.getChildren().addAll(verticalLayout, contentLayout);
        // Adding Buttons to grid
        mainHorizontalLayout.getStyleClass().add("main-hbox");
        Scene scene = new Scene(mainHorizontalLayout);
        scene.setFill(Color.TRANSPARENT);
        //  Getting the stylesheet file
        scene.getStylesheets().add(stylesBaseLocal + "main.css");
        scene.getStylesheets().add(stylesBaseLocal + "settings.css");
        mainWindow.setScene(scene);
        // Handle navigation clicks

        reusableComponents.swapLayouts(alarmsButton, contentLayout, alarmsLayout);
        reusableComponents.swapLayouts(newAlarmButton, contentLayout, newAlarmLayout);
        reusableComponents.swapLayouts(settingsButton, contentLayout, settingsLayout);

        exitAppButton.setOnAction(e -> {
            // mainWindow.close();
            //  mainWindow.setOnCloseRequest(event -> {
            // Optionally prompt the user for confirmation or perform cleanup tasks
            // Hide the primary stage instead of closing it
            mainWindow.hide();
            e.consume();

        });
        mainWindow.setX(980);
        mainWindow.show();
        BackgroundTaskHandler BackgroundTaskHandler = new BackgroundTaskHandler();

        ScheduledService<Void> backgroundService;
        backgroundService = BackgroundTaskHandler.getBackgroundService(alarmPopUpWindowStage);
        backgroundService.setPeriod(Duration.minutes(1));
        backgroundService.start();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
