package com.example.booksclient;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.List;


public class BooksController {
    @FXML
    private Label welcomeText;

    @FXML
    private ListView<String> bookListView;

    @FXML
    protected void onFetchBooksClicked() {
        welcomeText.setText("Welcome to JavaFX Application!");
        String booksJson = BooksSDK.fetchBooks("Java programming", 0, 5);
        List<String> books = parseBooksJson(booksJson); // Parse JSON string to list of books
        bookListView.getItems().clear();
        bookListView.getItems().addAll(books);
    }

    private List<String> parseBooksJson(String json) {

        return BooksParser.parseBooksJson(json);
    }

}