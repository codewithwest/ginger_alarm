package com.example.ginger_alarm;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Layouts {
    VBox alarmsLayout, newAlarmLayout;
    public  VBox alarmsLayout(){
        alarmsLayout = new VBox();
        alarmsLayout.setPadding(new Insets(.5, .5, .5, .2));

        Label timeLabel = new Label();
        timeLabel.setId("timeLabel"); // For styling purposes
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                LocalDateTime currentTime = LocalDateTime.now();
                String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                timeLabel.setText(formattedTime);
            }
        };
        timer.start();
        alarmsLayout.getStyleClass().add("timeCounter");

        alarmsLayout.getChildren().addAll(timeLabel);
        alarmsLayout.setAlignment(Pos.CENTER);

        return alarmsLayout;
    }

    public VBox newAlarmLayout() {
        newAlarmLayout = new VBox();
        newAlarmLayout.setPadding(new Insets(50));
        newAlarmLayout.setAlignment(Pos.CENTER);
        // Alarm label
        Label alarmLabel = new Label("New Alarm");
        alarmLabel.getStyleClass().add("labelText");

        alarmLabel.setAlignment(Pos.TOP_CENTER);
        //Alarm Message
        VBox alarmMessage = new VBox();

        TextField alarmMessageInput = new TextField();
        alarmMessageInput.setPrefHeight(60);
        alarmMessage.getChildren().addAll(alarmMessageInput);
        alarmMessageInput.getStyleClass().add("messageInputs");
        alarmMessageInput.getStyleClass().add("labelText");

        GridPane newAlarmSetGridContainer = new GridPane();
        newAlarmSetGridContainer.setVgap(8);
        newAlarmSetGridContainer.setHgap(10);
        // Time Label on same line 0,2,3 Column 0 Row
        Label alarmHours = new Label("HH");
        alarmHours.setAlignment(Pos.CENTER);
        alarmHours.getStyleClass().add("labelText");

        GridPane.setConstraints(alarmHours, 0, 0);
        Label alarmHoursMinutesDivider = new Label(" : ");
        alarmHoursMinutesDivider.getStyleClass().add("labelText");

        GridPane.setConstraints(alarmHoursMinutesDivider, 1, 0);
        Label alarmMinutes = new Label("MM");
        alarmMinutes.setAlignment(Pos.CENTER);
        alarmMinutes.getStyleClass().add("labelText");

        GridPane.setConstraints(alarmHours, 2, 0);

        // Time Label on same line 0,2,3 Column 1 Row
        TextField alarmHoursInput = new TextField("12");
        alarmHoursInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            alarmHoursInput.setText(newValue.replaceAll("[^\\d]", oldValue.replaceAll("[^0-9]{2}", "")));
        });
        GridPane.setConstraints(alarmHoursInput ,0, 1);
        Label alarmHoursMinutesInputDivider = new Label(" : ");
        GridPane.setConstraints(alarmHoursMinutesInputDivider, 1, 1);
        TextField alarmMinutesInput = new TextField("30");
        alarmMinutesInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            alarmMinutesInput.setText(newValue.replaceAll("[^\\d]", oldValue.replaceAll("[^0-9]{2}", "")));
        });
        GridPane.setConstraints(alarmMinutesInput, 2, 1);
        alarmHoursInput.setPromptText("06");
        alarmMinutesInput.setPromptText("30");
        alarmHoursInput.getStyleClass().add("timeInputs");
        alarmMinutesInput.getStyleClass().add("timeInputs");
        // Alarm Repeat box
        newAlarmSetGridContainer.setAlignment(Pos.CENTER_LEFT);
        VBox alarmRepeat = new VBox();

        CheckBox alarmRepeatCheckBox = new CheckBox("Repeat");
        alarmRepeatCheckBox.getStyleClass().add("labelText");

        TilePane alarmRepeatDays = new TilePane();
        CheckBox alarmRepeatCheckBoxMon = new CheckBox("Mon");
        alarmRepeatCheckBoxMon.setPrefWidth(70);
        alarmRepeatCheckBoxMon.getStyleClass().add("labelText");
        CheckBox alarmRepeatCheckBoxTue = new CheckBox("Tue");
        alarmRepeatCheckBoxTue.setPrefWidth(70);
        alarmRepeatCheckBoxTue.getStyleClass().add("labelText");
        CheckBox alarmRepeatCheckBoxWed = new CheckBox("Wed");
        alarmRepeatCheckBoxWed.setPrefWidth(70);
        alarmRepeatCheckBoxWed.getStyleClass().add("labelText");
        CheckBox alarmRepeatCheckBoxThur = new CheckBox("Thu");
        alarmRepeatCheckBoxThur.setPrefWidth(70);
        alarmRepeatCheckBoxThur.getStyleClass().add("labelText");
        CheckBox alarmRepeatCheckBoxFri = new CheckBox("Fri");
        alarmRepeatCheckBoxFri.setPrefWidth(70);
        alarmRepeatCheckBoxFri.getStyleClass().add("labelText");
        CheckBox alarmRepeatCheckBoxSat = new CheckBox("Sat");
        alarmRepeatCheckBoxSat.setPrefWidth(70);

        alarmRepeatCheckBoxSat.getStyleClass().add("labelText");
        CheckBox alarmRepeatCheckBoxSun = new CheckBox("Sun");
        alarmRepeatCheckBoxSun.setPrefWidth(70);


        alarmRepeatCheckBoxSun.getStyleClass().add("labelText");
        alarmRepeatDays.getChildren().addAll(alarmRepeatCheckBoxMon,
                alarmRepeatCheckBoxTue, alarmRepeatCheckBoxWed,
                alarmRepeatCheckBoxThur,alarmRepeatCheckBoxFri,
                alarmRepeatCheckBoxSat, alarmRepeatCheckBoxSun);
        alarmRepeatDays.setAlignment(Pos.TOP_LEFT);
        alarmRepeatDays.setPrefWidth(300);
        alarmRepeatDays.setHgap(10);
        alarmRepeatDays.setVgap(10);

        Button addNewAlarmButton = new Button("Create Alarm");
        addNewAlarmButton.getStyleClass().add("labelText");

        addNewAlarmButton.setAlignment(Pos.CENTER);
        addNewAlarmButton.setPadding(new Insets(20));
        alarmRepeat.setSpacing(30);
        addNewAlarmButton.getStyleClass().add("setAlarmButton");

        alarmRepeat.getChildren().addAll(alarmRepeatCheckBox, new Group(alarmRepeatDays),addNewAlarmButton);

        newAlarmSetGridContainer.getChildren().addAll(
                alarmLabel,
                alarmHours, alarmHoursMinutesDivider, alarmMinutes,
                alarmHoursInput, alarmHoursMinutesInputDivider, alarmMinutesInput
                );

        newAlarmLayout.getChildren().addAll(alarmLabel, alarmMessage,newAlarmSetGridContainer,alarmRepeat);
        newAlarmLayout.setPadding(new Insets(20));
        newAlarmLayout.setSpacing(10);
        List<String> days = new ArrayList<>();

        // Retrieve Alarm Data
        addNewAlarmButton.setOnAction(e->{
            String hours = alarmHoursInput.getText();
            String minutes = alarmMinutesInput.getText();
            try{
                if (Integer.parseInt(hours) > 23 || Integer.parseInt(hours) < 0) return;
                if (Integer.parseInt(minutes) > 59 || Integer.parseInt(minutes) < 0) return;
                if (alarmMessageInput.getText().length() < 3) return;
            }catch (Exception err){
                e.consume();
                return;
            }

            if(alarmRepeatCheckBoxMon.isSelected())days.add("Monday");
            if(alarmRepeatCheckBoxTue.isSelected())days.add("Tuesday");
            if(alarmRepeatCheckBoxWed.isSelected())days.add("Wednesday");
            if(alarmRepeatCheckBoxThur.isSelected())days.add("Thursday");
            if(alarmRepeatCheckBoxFri.isSelected())days.add("Friday");
            if(alarmRepeatCheckBoxSat.isSelected())days.add("Saturday");
            if(alarmRepeatCheckBoxSun.isSelected())days.add("Sunday");

            String alarmMes = "message: " + alarmMessageInput.getText()+",";
            String chosenTime = "time: "+ hours + ":" + minutes+",";
            String repeatedTime = "repeated: " + alarmRepeatCheckBox.isSelected()+",";
            String numOfDays = "days: "+ Lists.newArrayList(Sets.newHashSet(days));

            String compiledAlarmData = "{" + alarmMes + chosenTime + repeatedTime + numOfDays + "}";

            // Write to file
            System.out.println(compiledAlarmData);
            FileDirManager.writeToAlarmsFile(compiledAlarmData);
            // Reset Alarm Setter
            alarmHoursInput.clear();
            alarmMinutesInput.clear();
            alarmMessageInput.clear();
            alarmRepeatCheckBox.setSelected(false);
            alarmRepeatCheckBoxMon.setSelected(false);
            alarmRepeatCheckBoxTue.setSelected(false);
            alarmRepeatCheckBoxWed.setSelected(false);
            alarmRepeatCheckBoxThur.setSelected(false);
            alarmRepeatCheckBoxFri.setSelected(false);
            alarmRepeatCheckBoxSat.setSelected(false);
            alarmRepeatCheckBoxSun.setSelected(false);
        });

        return newAlarmLayout;
    }
}
