package com.example.booksclient.controllers;

import com.example.booksclient.models.domain.Book;
import com.example.booksclient.NativeApi;
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
    private boolean isFavorite;
    private String bookId;
    private String bookJson;
    private Runnable refreshPreviousSceneCallback;

    public void setBook(Book book) {
        bookId = book.getId();
        bookJson = book.getBookJson();
        bookImage.setImage(new Image(book.getImageUrl()));
        bookTitle.setText(book.getTitle());
        bookAuthors.setText("Authors: " + book.getAuthors());
        var description = book.getDescription();
        if (description != null && !description.isEmpty()) {
            bookDescription.setText(description);
        } else {
            bookDescription.setText("No description available");
        }
        isFavorite = NativeApi.isFavorite(book.getId());
        setFavoriteLabelText();
        buyLink = book.getBuyUrl();
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

    public void setRefreshPreviousSceneCallback(Runnable callback) {
        this.refreshPreviousSceneCallback = callback;
    }

    @FXML
    private void favoriteBook() {
        if (isFavorite) {
            NativeApi.removeFromFavorites(bookId);
        } else {
            NativeApi.addToFavorites(bookId, bookJson);
        }
        isFavorite = !isFavorite;
        setFavoriteLabelText();
    }

    @FXML
    private void buyBook() {
        hostServices.showDocument(buyLink);
    }

    @FXML
    private void goBack() {
        if (refreshPreviousSceneCallback != null) {
            refreshPreviousSceneCallback.run();
        }

        Stage stage = (Stage) bookImage.getScene().getWindow();
        Scene scene = bookImage.getScene();
        scene.setRoot(previousScene);
        stage.setScene(scene);
    }

    private void setFavoriteLabelText() {
        var favoriteText = isFavorite ? "Unfavorite Book" : "Favorite Book";
        bookFavorite.setText(favoriteText);
    }
}
