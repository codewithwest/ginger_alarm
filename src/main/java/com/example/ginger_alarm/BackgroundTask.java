package com.example.ginger_alarm;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

public class BackgroundTask  {
@NotNull
protected static ScheduledService<Void> getBackgroundService() {
    ScheduledService<Void> backgroundService;
    backgroundService = new ScheduledService<>() {
        @Override
        protected Task<Void> createTask() {
            return new Task<>() {
                @Override
                protected Void call() throws Exception {
                    // Perform your background task logic here
                    FileDirManager.createFileDir("logs");
                    System.out.println("Background task running... at Time:" + FileDirManager.currentDateTime());
                    Process process = Runtime.getRuntime().exec("curl -o https://github.com/codewithwest/ProjectGinger/blob/main/Power_down.py");
                    //  Update UI elements using Platform.runLater()
                    //  Platform.runLater(() -> statusLabel.setText("Task running"));
                    return null;
                }
            };
        }
    };
//    backgroundService.notify();
    backgroundService.setPeriod(Duration.minutes(1)); // Execute every 1 min
    return backgroundService;
}
}
