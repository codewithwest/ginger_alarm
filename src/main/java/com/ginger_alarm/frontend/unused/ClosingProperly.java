package com.ginger_alarm.frontend.unused;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ClosingProperly extends Application  {
    ScenesCollection scenes;
    Stage window;
    Button button;
    Scene mainScene, alarmsScene, setAlarmScene;

    @Override
    public void start(Stage primaryStage) throws Exception {

       window = primaryStage;
       window.setTitle("Main Window");
        window.setOnCloseRequest(e-> {
            e.consume();
            closeProgram();
        });
       button  = new Button("Click Me!");
//       button.setOnAction(e->{
//           boolean result = ConfirmBox.display("window Alert", "Do you wanna send  it?");
//           System.out.println(result);
//       });

        button.setOnAction(e->closeProgram());



       StackPane layout = new StackPane();
       layout.getChildren().add(button);
       Scene scene = new Scene(layout, 200,200);
       window.setScene(scene);
       window.show();
    }

    private void closeProgram() {
        boolean answer;
        answer = ConfirmBox.display("Alert....", "Exit?");

        if (answer){
            System.out.println("File Saved");
            System.out.println("Closing Window");
            window.close();
        }else {
            return;
        }


    }

    public static void main(String[] args) {
            launch(args);
    }

}
