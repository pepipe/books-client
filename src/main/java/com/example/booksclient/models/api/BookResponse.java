package com.example.booksclient.models.api;

public class BookResponse {
    String id;
    VolumeInfo volumeInfo;
    SaleInfo saleInfo;

    public String getId() {
        return id;
    }

    public String getTitle(){
        return volumeInfo.title;
    }

    public String getAuthors(){
        return volumeInfo.authors.toString();
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

    public String getBuyLink(){
        return saleInfo.buyLink;
    }
}
