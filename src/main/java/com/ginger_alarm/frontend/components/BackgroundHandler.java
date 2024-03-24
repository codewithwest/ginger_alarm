package com.ginger_alarm.frontend.components;

import javafx.application.Platform;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class BackgroundHandler {

    boolean stateWindow = false;
    public void handleBackgroundScene(Stage stage) {
        Platform.setImplicitExit(false);

        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        BufferedImage image;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/assets/images/dog-icon.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //image dimensions must be 16x16 on windows, works for me
        final TrayIcon trayIcon = new TrayIcon(image, "Ginger Alarm");
        final SystemTray tray = SystemTray.getSystemTray();
        //Listener left click XD
        trayIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (event.getButton() == MouseEvent.BUTTON1) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if (stateWindow) stage.hide();
                            else stage.show();
                            stateWindow = !stateWindow;
                        }
                    });
                }
            }
        });
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
        }


    }
}


