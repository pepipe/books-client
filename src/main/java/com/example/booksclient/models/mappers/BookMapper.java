package com.example.booksclient.models.mappers;

import com.example.booksclient.models.api.BookResponse;
import com.example.booksclient.models.domain.Book;
import com.google.gson.Gson;

public class BookMapper {
    private BookMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Book toDomain(BookResponse bookResponse) {
        if (bookResponse == null) {
            return null;
        }

        Book book = new Book();
        book.setId(bookResponse.getId());
        book.setTitle(bookResponse.getTitle());
        book.setAuthors(bookResponse.getAuthors());
        book.setDescription(bookResponse.getDescription());
        book.setImageUrl(bookResponse.getThumbnail());
        book.setThumbnailUrl(bookResponse.getSmallThumbnail());
        book.setBuyUrl(bookResponse.getBuyLink());

        Gson gson = new Gson();
        String bookJson = gson.toJson(bookResponse);
        book.setBookJson(bookJson);

        return book;
    }
}