module com.example.booksclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;

    exports com.example.booksclient;
    exports com.example.booksclient.services;
    exports com.example.booksclient.models.parsers;
    exports com.example.booksclient.models.domain;

    opens com.example.booksclient to com.google.gson, javafx.fxml;
    opens com.example.booksclient.models.api to com.google.gson;
    opens com.example.booksclient.services to com.google.gson, javafx.fxml;
    opens com.example.booksclient.models.parsers to com.google.gson, javafx.fxml;
    exports com.example.booksclient.controllers;
    opens com.example.booksclient.controllers to com.google.gson, javafx.fxml;
}