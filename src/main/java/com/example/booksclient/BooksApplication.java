package com.example.booksclient;

import com.example.booksclient.controllers.BooksController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BooksApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BooksApplication.class.getResource("books-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        BooksController controller = fxmlLoader.getController();
        controller.setHostServices(getHostServices());
        scene.getStylesheets().add(BooksApplication.class.getResource("dark-theme.css").toExternalForm());
        stage.setTitle("Google Books");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}