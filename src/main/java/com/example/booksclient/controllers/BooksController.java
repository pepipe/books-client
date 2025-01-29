package com.example.booksclient.controllers;

import com.example.booksclient.BooksApplication;
import com.example.booksclient.models.domain.Book;
import com.example.booksclient.models.parsers.BooksParser;
import com.example.booksclient.NativeApi;
import com.example.booksclient.utils.FavoriteBooksHelper;

import javafx.application.HostServices;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private Button toggleFavorites;

    @FXML
    private ProgressIndicator loadingIndicator;

    private static final int FETCH_BOOKS_MAX_RESULTS = 20;

    public void setHostServices(HostServices get) {
        this.hostServices = get;
    }

    private HostServices hostServices;

    private boolean favoritesOn = false;
    private int currentOffset = 0;
    private boolean isLoading = false;


    @FXML
    public void initialize() {
        loadBooks();

        booksScroll.vvalueProperty().addListener(
                (observable, oldValue, newValue) ->
        {
            if (newValue.doubleValue() > 0.8 && !isLoading) { // Trigger fetch when 80% scrolled
                currentOffset += FETCH_BOOKS_MAX_RESULTS;
                loadBooks();
            }
        });

    }

    @FXML
    private void handleToggleFavorites() {
        favoritesOn = !favoritesOn;
        booksGrid.getChildren().clear();
        loadBooks();
    }

    private List<Book> parseBooksJson(String json) {

        return BooksParser.parseBooksJson(json);
    }

    private void loadBooks() {
        if (isLoading) return;

        Task<String> fetchBooksTask;
        loadingIndicator.setVisible(true);
        isLoading = true;

        if (favoritesOn) {
            toggleFavorites.setText("Favorites On");
            fetchBooksTask = new Task<>() {
                @Override
                protected String call() {
                    List<String> favoritesBooks = NativeApi.getFavoriteBooks();

                    if (favoritesBooks == null) {
                        throw new IllegalStateException("Native method returned null for favorite books");
                    }

                    if (favoritesBooks.isEmpty()) {
                        System.out.println("Warning: No favorite books found.");
                    }

                    return FavoriteBooksHelper.combineJsonStrings(favoritesBooks);
                }
            };
        } else {
            toggleFavorites.setText("Favorites Off");
            fetchBooksTask = new Task<>() {
                @Override
                protected String call() {
                    String result = NativeApi.fetchBooks("iOS", currentOffset, FETCH_BOOKS_MAX_RESULTS);

                    if (result == null) {
                        throw new IllegalStateException("Native method fetchBooks returned null");
                    }

                    return result;
                }
            };
        }

        fetchBooksTask.setOnSucceeded(_ -> {
            loadingIndicator.setVisible(false);
            String booksJson = fetchBooksTask.getValue();
            List<Book> books = parseBooksJson(booksJson);
            populateBooksGrid(books);
            isLoading = false;
        });

        fetchBooksTask.setOnFailed(_ -> {
            loadingIndicator.setVisible(false);
            Throwable throwable = fetchBooksTask.getException();
            System.err.println("Error fetching books: " + throwable.getMessage());
            isLoading = false;
        });

        // Run the Task in a separate thread
        Thread thread = new Thread(fetchBooksTask);
        thread.setDaemon(true);
        thread.start();
    }

    private void refreshBooksGrid() {
        if (!favoritesOn)
            return; //only refresh if we have the favorites filter, if not we want to keep the previous data
        booksGrid.getChildren().clear();
        loadBooks();
    }

    private void populateBooksGrid(List<Book> books) {
        int columns = 2; // Two books per row
        int currentItemsCount = booksGrid.getChildren().size();
        int row = currentItemsCount / columns;
        int column = currentItemsCount % columns;

        for (Book book : books) {
            String title = book.getTitle();
            String thumbnailUrl = book.getThumbnailUrl();

            // Create UI components
            VBox bookBox = new VBox(5);
            bookBox.setAlignment(javafx.geometry.Pos.CENTER);

            ImageView thumbnail = new ImageView(new Image(thumbnailUrl));
            thumbnail.setFitWidth(100);
            thumbnail.setFitHeight(150);

            Label bookTitle = new Label(title);
            bookTitle.setMaxWidth(100); // Limit width to fit under the thumbnail
            bookTitle.setWrapText(false); // No multiline titles
            bookTitle.setTextOverrun(OverrunStyle.ELLIPSIS);

            bookBox.getChildren().addAll(thumbnail, bookTitle);

            bookBox.setOnMouseClicked(_ -> openDetailsView(book));

            booksGrid.add(bookBox, column, row);
            column++;
            if (column >= columns) {
                column = 0;
                row++;
            }
        }
    }

    private void openDetailsView(Book book) {
        try {
            FXMLLoader loader = new FXMLLoader(BooksApplication.class.getResource("book-details-view.fxml"));
            Parent root = loader.load();

            // Pass book data to the details controller
            BookDetailsController detailsController = loader.getController();
            detailsController.setHostServices(hostServices);
            detailsController.setBook(book);
            detailsController.setPreviousScene(booksScroll.getScene().getRoot());
            detailsController.setRefreshPreviousSceneCallback(this::refreshBooksGrid);

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