module com.example.booksclient {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.booksclient to javafx.fxml;
    exports com.example.booksclient;
}