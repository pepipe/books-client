package com.example.booksclient.models;

public class Book {
    String id;
    VolumeInfo volumeInfo;
    SaleInfo saleInfo;

    public String getId() {
        return id;
    }

    public String getTitle(){
        return volumeInfo.title;
    }
}
