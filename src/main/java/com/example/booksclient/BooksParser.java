package com.example.booksclient;

import com.example.booksclient.models.api.BookResponse;
import com.example.booksclient.models.api.GoogleBooksResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class BooksParser {

    private BooksParser() {
        throw new IllegalStateException("Utility class");
    }

    public static List<BookResponse> parseBooksJson(String json) {
        List<BookResponse> books = new ArrayList<>();
        Gson gson = new Gson();

        try {
            // Convert the JSON to a Java object using the classes above
            GoogleBooksResponse response = gson.fromJson(json, GoogleBooksResponse.class);

            if (response != null && response.hasItems()) {
                books.addAll(response.getItems());
            }
        } catch (Exception e) {
            // You can log or handle JSON parsing exceptions here
            e.printStackTrace();
        }

        return books;
    }
}