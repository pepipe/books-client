module com.example.booksclient {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    requires com.google.gson;

    opens com.example.booksclient to javafx.fxml;
    opens com.example.booksclient.models to com.google.gson;
    exports com.example.booksclient;
}