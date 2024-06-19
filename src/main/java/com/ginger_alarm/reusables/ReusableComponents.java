package com.ginger_alarm.reusables;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class ReusableComponents {
    public Button newBasicButton(String btnName, List<String> btnStyleTags){
        Button newButton = new Button();

        newButton.setText(btnName);

        for (String styleTag: btnStyleTags) {
            newButton.getStyleClass().add(styleTag);
        }
        return newButton;
    }

    public void swapLayouts(Button btnClicked, StackPane stackLayout, VBox incomingLayout){
        btnClicked.setOnAction(e->{
            stackLayout.getChildren().removeFirst();
            stackLayout.getChildren().add(incomingLayout);
        });
    }

}
