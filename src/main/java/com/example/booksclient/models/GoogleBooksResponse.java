package com.example.booksclient.models;

import java.util.List;

public class GoogleBooksResponse {
    List<Book> items;

    public Boolean hasItems(){
        return items != null && !items.isEmpty();
    }

    public List<Book> getItems() {
        return items;
    }
}
