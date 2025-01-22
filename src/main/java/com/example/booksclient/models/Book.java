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

    public String getDescription(){
        return volumeInfo.description;
    }

    public String getSmallThumbnail(){
        return volumeInfo.imageLinks.smallThumbnail;
    }

    public String getThumbnail(){
        return volumeInfo.imageLinks.thumbnail;
    }
}
