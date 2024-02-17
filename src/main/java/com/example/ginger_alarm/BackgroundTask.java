package com.example.ginger_alarm;
import javafx.application.Platform;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

public class BackgroundTask {

    @NotNull
    protected ScheduledService<Void> getBackgroundService(Stage secondaryStage) {

        ScheduledService<Void> backgroundService = new ScheduledService<>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<>() {
                    @Override
                    protected Void call() throws Exception {
                        // Perform your background task logic here

                        if (!secondaryStage.isShowing()) {
                            Platform.runLater(()->{
                                try {
                                    secondaryStage.setScene(new AlarmPopUp(secondaryStage).AlarmPopUpScene());
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            });

                            Platform.runLater(secondaryStage::show);
                        }
                        else {
                            System.out.println("Stop");
                        }
                        FileDirManager.createFileDir("logs");
                        System.out.println("Background task running... at Time:" + FileDirManager.currentDateTime());
//                        Process process = Runtime.getRuntime().exec("curl -o https://github.com/codewithwest/ProjectGinger/blob/main/Power_down.py");
                        return null;
                    }
                };
            }
        };
        //    backgroundService.notify();
        backgroundService.setPeriod(Duration.seconds(5)); // Execute every 1 min
        return backgroundService;
    }

}
