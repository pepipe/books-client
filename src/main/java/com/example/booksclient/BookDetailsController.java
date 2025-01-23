package com.example.booksclient;

import com.example.booksclient.models.api.BookResponse;
import com.example.booksclient.services.GoogleBooksService;
import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BookDetailsController {

    @FXML
    private ImageView bookImage;

    @FXML
    private Label bookTitle;

    @FXML
    private Label bookAuthors;

    @FXML
    private Label bookDescription;

    @FXML
    private Button bookFavorite;

    @FXML
    private Button bookBuy;

    private HostServices hostServices;
    private Parent previousScene;
    private String buyLink;

    public void setBook(BookResponse book) {
        bookImage.setImage(new Image(book.getSmallThumbnail()));
        bookTitle.setText(book.getTitle());
        bookAuthors.setText("Authors: " + book.getAuthors());
        var description = book.getDescription();
        if (description != null && !description.isEmpty()) {
            bookDescription.setText(description);
        } else {
            bookDescription.setText("No description available");
        }
        var isFavorite = GoogleBooksService.isFavorite(book.getId());
        var favoriteText = isFavorite ? "Unfavorite Book" : "Favorite Book";
        bookFavorite.setText(favoriteText);
        buyLink = book.getBuyLink();
        if (buyLink == null || buyLink.isEmpty()) {
            bookBuy.setText("No buy options available");
            bookBuy.setDisable(true);
        }
    }

    public void setPreviousScene(Parent previousScene) {
        this.previousScene = previousScene;
    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }


    @FXML
    private void favoriteBook(){

    }

    @FXML
    private void buyBook() {
        hostServices.showDocument(buyLink);
    }

    @FXML
    private void goBack() {
        Stage stage = (Stage) bookImage.getScene().getWindow();
        Scene scene = bookImage.getScene();
        scene.setRoot(previousScene);
        stage.setScene(scene);
    }
}
