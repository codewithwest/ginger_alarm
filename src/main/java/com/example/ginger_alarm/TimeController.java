package com.example.ginger_alarm;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Box;

public class TimeController {
    @FXML
    private Label welcomeText;
    @FXML
    private Box newBox;

    @FXML
    protected void onAlarmsClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    protected void onCreateAlarmClick() {
        newBox.setWidth(50);
        newBox.setHeight(50);
    }
    protected void onSettingsClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}