package com.example.booksclient;

import java.util.List;

public class BooksSDK {
    private BooksSDK() {
        throw new IllegalStateException("Utility class");
    }

    static {
        try {
            System.loadLibrary("BooksSDK");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Could not load native library: " + e.getMessage());
            throw e;
        }
    }

    public static native String fetchBooks(String query, int startIndex, int maxResults);
    public static native void addToFavorites(String bookId, String bookJson);
    public static native boolean isFavorite(String bookId);
    public static native List<String> getFavoriteBooks();
}
