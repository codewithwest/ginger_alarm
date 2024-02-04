package com.example.ginger_alarm;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class AlarmPopUp {


    Button dismissButton, snoozeButton;
    Stage currentStage;
    MediaPlayer playSound ;

    AlarmPopUp(@NotNull Stage currentStage) {
        currentStage.setX(10000);
        currentStage.setAlwaysOnTop(true);
        currentStage.setY(0);
        this.currentStage = currentStage;


    }

    public HBox dismissOrSnooze() {
        dismissButton = new Button();
        dismissButton.setText("Dismiss");
        dismissButton.getStyleClass().add("dismissButton");
        snoozeButton = new Button();
        snoozeButton.setText("Snooze");
        snoozeButton.getStyleClass().add("dismissButton");
        HBox btnContainer = new HBox();
        btnContainer.setPadding(new Insets(5));
        btnContainer.setSpacing(10);
        dismissButton.setOnAction(e -> {
            currentStage.close();
//            playSound.stop();

        });
        snoozeButton.setOnAction(e -> {
            currentStage.close();
//            playSound.stop();

        });

        btnContainer.getChildren().addAll(dismissButton, snoozeButton);
        btnContainer.alignmentProperty().set(Pos.CENTER);
        return btnContainer;
    }

    public VBox setAlarmPopUpWindowVBox() throws Exception {
        VBox alarmPopUpCont = new VBox();
        alarmPopUpCont.setSpacing(10);
        alarmPopUpCont.setPadding(new Insets(15));
        Text alarmMessage = new Text("This is an alarm Message is not the green sense of it is an alarm Message is an alarm Message");
        alarmMessage.setTextAlignment(TextAlignment.CENTER);
        alarmMessage.setWrappingWidth(220);
        alarmMessage.getStyleClass().add("labelText");
        Text alarmTime = new Text("15:00");
        alarmMessage.getStyleClass().add("labelText");
        alarmPopUpCont.getStyleClass().add("main-vbox");
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            // Update your scene elements here


//            if (alarmPopUpCont.getBackground().toString().equals("TRANSPARENT")) {
//                Platform.runLater(() -> alarmPopUpCont.setBorder(Border.EMPTY));
//            } else {
//                Platform.runLater(() -> alarmPopUpCont.setBorder(Border));
//            }
        }, 0, 2, TimeUnit.SECONDS);
        alarmPopUpCont.getChildren().addAll(alarmMessage, alarmTime, this.dismissOrSnooze());

        return alarmPopUpCont;
    }

    public Scene AlarmPopUpScene() throws Exception {
        Scene scene = new Scene(this.setAlarmPopUpWindowVBox());
        scene.setFill(Color.TRANSPARENT);
        //  Getting the stylesheet file
        scene.getStylesheets().add("file:/samba/public/documents/github/ginger_alarm/src/main/resources/style/alarmPopUp.css");
        playSound = new AlarmDispatchHandler().createMusicToPlay();
//        playSound.play();


        return scene;
    }
}