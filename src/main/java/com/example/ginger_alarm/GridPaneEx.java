package com.example.ginger_alarm;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Objects;

public class GridPaneEx  extends Application {
    Stage window;
    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        window.setTitle("Main");


        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Name Label
        Label nameLabel = new Label("Username: ");
        GridPane.setConstraints(nameLabel, 0, 0);
        // Name Input

        TextField nameInput = new TextField("username");
        GridPane.setConstraints(nameInput, 1, 0);


        // Name Label
        Label passLabel = new Label("Password: ");
        GridPane.setConstraints(passLabel, 0, 1);
        // Name Input

        TextField passInput = new TextField();
        passInput.setMinHeight(30);
        passInput.setPromptText("password");
        GridPane.setConstraints(passInput, 1, 1);

        DatePicker newDate= new DatePicker();
        GridPane.setConstraints(newDate, 1, 5);
        //Login Button
        Button loginButton = new Button("Login!");
        loginButton.setOnAction(e->{
            if(Objects.equals(nameInput.getText(), "Username")){
                return;
//                nameInput.setBorder(new Border(BorderStrokeStyle.DOTTED));
            }
            System.out.println(nameInput.getText() + passInput.getText());
        });
        GridPane.setConstraints(loginButton, 1,2);

        grid.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, loginButton, newDate);

        Scene scene = new Scene(grid, 300, 200);
        window.setScene(scene);


        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
