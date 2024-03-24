package com.ginger_alarm.backend;

import com.ginger_alarm.frontend.components.AlarmPopUp;
import javafx.application.Platform;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

public class BackgroundTaskHandler {
    DBManager dbManager = new DBManager();
    FileDirManager fileManager = new FileDirManager();

    @NotNull
    public ScheduledService<Void> getBackgroundService(Stage secondaryStage) {

        ScheduledService<Void> backgroundService = new ScheduledService<>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<>() {
                    @Override
                    protected Void call() throws Exception {
                        // Perform your background task logic here
                        dbManager.GetCollection();



                            if (!secondaryStage.isShowing() && fileManager.checkForMatchingAlarmTime("Alarm Dispached")) {
                                Platform.runLater(()->{
                                    try {
                                        secondaryStage.setScene(new AlarmPopUp(secondaryStage).AlarmPopUpScene());
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                });

                                Platform.runLater(secondaryStage::show);
                            }

                        System.out.println("Background task running... at Time:" );

                        ;

                        //  Process process = Runtime.getRuntime().exec("curl -o https://github.com/codewithwest/ProjectGinger/blob/main/Power_down.py");
                        // new DayToDate().GetNewAlarmDay("Friday", "19", "33");

                        return null;
                    }
                };
            }
        };
        //    backgroundService.notify();
//        backgroundService.setPeriod(Duration.seconds(5)); // Execute every 1 min
        return backgroundService;
    }

}