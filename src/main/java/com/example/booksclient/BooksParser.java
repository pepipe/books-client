package com.example.booksclient;

import com.example.booksclient.models.Book;
import com.example.booksclient.models.GoogleBooksResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class BooksParser {

    private BooksParser() {
        throw new IllegalStateException("Utility class");
    }

    public static List<String> parseBooksJson(String json) {
        List<String> bookTitles = new ArrayList<>();
        Gson gson = new Gson();

        try {
            // Convert the JSON to a Java object using the classes above
            GoogleBooksResponse response = gson.fromJson(json, GoogleBooksResponse.class);

            if (response != null && response.hasItems()) {
                // For each book item, collect the title
                for (Book book : response.getItems()) {
                    var title = book.getTitle();
                    if (title != null && !title.isEmpty()) {
                        bookTitles.add(title);
                    }
                }
            }
        } catch (Exception e) {
            // You can log or handle JSON parsing exceptions here
            e.printStackTrace();
        }

        return bookTitles;
    }
}