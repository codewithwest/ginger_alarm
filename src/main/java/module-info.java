module com.example.ginger_alarm {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires javafx.media;
    requires java.desktop;
    requires annotations;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;


    opens com.ginger_alarm.frontend to javafx.fxml;
    exports com.ginger_alarm.frontend.unused;
    opens com.ginger_alarm.frontend.unused to javafx.fxml;
    exports com.ginger_alarm.frontend.components;
    opens com.ginger_alarm.frontend.components to javafx.fxml;
    exports com.ginger_alarm;
    opens com.ginger_alarm to javafx.fxml;
}