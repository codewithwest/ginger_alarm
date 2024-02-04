package com.example.ginger_alarm;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

public class JavaCss  extends Application {
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

        CheckBox checkBox1 = new CheckBox("Repeat? ");
        GridPane.setConstraints(checkBox1, 1,6);


        ChoiceBox<String> dropdown = new ChoiceBox<>();
        dropdown.setValue("Now");
        dropdown.getItems().addAll("Alples");
        dropdown.getItems().addAll("Banana");
        dropdown.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue)->System.out.println(newValue));
        GridPane.setConstraints(dropdown, 1,7);


        //Login Button
        Button loginButton = new Button("Login!");
        loginButton.setOnAction(e->{
            System.out.println(nameInput.getText() + passInput.getText());
            System.out.println(checkBox1.isSelected());
            System.out.println(dropdown.getValue());
//            setUserAgentStylesheet(STYLESHEET_MODENA);
        });
        GridPane.setConstraints(loginButton, 1,2);





        grid.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, loginButton, newDate, checkBox1, dropdown);

        Scene scene = new Scene(grid, 500, 200);
        scene.getStylesheets().add(String.valueOf(getClass().getResource("/samba/public/documents/github/java/ginger_alarm/src/main/java/com/example/ginger_alarm/main.css")));
        window.setScene(scene);

        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
