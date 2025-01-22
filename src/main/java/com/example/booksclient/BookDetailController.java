package com.example.booksclient;

import com.example.booksclient.models.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BookDetailController {

    @FXML
    private ImageView bookImage;

    @FXML
    private Label bookTitle;

    @FXML
    private Label bookDescription;

    private Parent previousScene;

    public void setBook(Book book) {
        bookImage.setImage(new Image(book.getSmallThumbnail()));
        bookTitle.setText(book.getTitle());
        bookDescription.setText(book.getDescription());
    }

    public void setPreviousScene(Parent previousScene) {
        this.previousScene = previousScene;
    }

    @FXML
    private void goBack() {
        Stage stage = (Stage) bookImage.getScene().getWindow();
        stage.setScene(new Scene(previousScene));
    }
}
