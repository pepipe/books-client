package com.example.booksclient;

import com.example.booksclient.models.Book;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;


public class BooksController {

    @FXML
    private ScrollPane booksScroll;

    @FXML
    private GridPane booksGrid;

    @FXML
    public void initialize() {
        String booksJson = BooksSDK.fetchBooks("iOS", 0, 20);
        List<Book> books = parseBooksJson(booksJson); // Parse JSON string to list of books
        populateBooksGrid(books);
    }

    private List<Book> parseBooksJson(String json) {

        return BooksParser.parseBooksJson(json);
    }

    private void populateBooksGrid(List<Book> books) {
        int columns = 2; // Two books per row
        int row = 0;

        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            String title = book.getTitle();
            String thumbnailUrl = book.getSmallThumbnail();

            // Create UI components
            VBox bookBox = new VBox(5);
            bookBox.setAlignment(javafx.geometry.Pos.CENTER);

            ImageView thumbnail = new ImageView(new Image(thumbnailUrl));
            thumbnail.setFitWidth(100);
            thumbnail.setFitHeight(150);

            Label bookTitle = new Label(title);

            bookBox.getChildren().addAll(thumbnail, bookTitle);

            // Wrap in a button or add a click listener
            bookBox.setOnMouseClicked(event -> openDetailsView(book));

            // Add to grid
            int column = i % columns;
            if (i > 0 && column == 0) {
                row++; // Move to the next row
            }

            booksGrid.add(bookBox, column, row);
        }
    }

    private void openDetailsView(Book book) {
        try {
            FXMLLoader loader = new FXMLLoader(BooksApplication.class.getResource("book-details-view.fxml"));
            Parent root = loader.load();

            // Pass book data to the details controller
            BookDetailsController detailsController = loader.getController();
            detailsController.setBook(book);
            detailsController.setPreviousScene(booksScroll.getScene().getRoot());

            // Switch scenes
            Stage stage = (Stage) booksScroll.getScene().getWindow();
            Scene scene = booksScroll.getScene();
            scene.setRoot(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}