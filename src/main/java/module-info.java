module com.example.booksclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    requires com.google.gson;

    opens com.example.booksclient.models to com.google.gson;
    exports com.example.booksclient;
    opens com.example.booksclient to com.google.gson, javafx.fxml;
}