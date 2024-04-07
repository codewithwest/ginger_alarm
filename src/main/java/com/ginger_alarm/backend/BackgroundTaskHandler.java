package com.ginger_alarm.backend;

import com.ginger_alarm.frontend.components.AlarmPopUp;
import javafx.application.Platform;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

/**
 * Creates the background task instance
 * and methods to implement
 */
public class BackgroundTaskHandler {
    DBManager dbManager = new DBManager();
    FileDirManager fileManager = new FileDirManager();

    /**
     * @param secondaryStage
     * takes in secondary stage to handle alarm dispatch
     * @return returns null
     */
    @NotNull
    public ScheduledService<Void> getBackgroundService(Stage secondaryStage) {
        return new ScheduledService<>() {
            /**
             * creates the task from scheduler instance
             * @return return a new task
             */
            @Override
            protected Task<Void> createTask() {
                return new Task<>() {
                    /**
                     * @return null
                     * @throws Exception call stack exception if stage
                     * methods or operations do not execute well
                     */
                    @Override
                    protected Void call() throws Exception {
                        // calls dbCollection to seed alarms into
                        // local file before new alarm match check
                        dbManager.GetCollection();

                        if (!secondaryStage.isShowing() && fileManager.checkForMatchingAlarmTime("Alarm Dispached")) {
                            Platform.runLater(()->{
                                try {
                                    secondaryStage.setScene(new AlarmPopUp(secondaryStage).AlarmPopUpScene());
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            });
                            //renders hidden alarm pop up stage (secondary stage)
                            Platform.runLater(secondaryStage::show);
                        }

                        System.out.println("Background task running... at Time:" );

                        // new DayToDate().GetNewAlarmDay("Friday", "19", "33");
                        return null;
                    }
                };
            }
        };
    }

}