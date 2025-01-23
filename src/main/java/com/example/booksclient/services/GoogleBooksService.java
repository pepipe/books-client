package com.example.booksclient.services;

import java.util.List;

public class GoogleBooksService {
    private GoogleBooksService() {
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

    public interface FetchBooksCallback {
        void onResult(String resultJson);
    }

    public static native void fetchBooksAsync(FetchBooksCallback callback, String query, int startIndex, int maxResults);
    public static native String fetchBooks(String query, int startIndex, int maxResults);
    public static native void addToFavorites(String bookId, String bookJson);
    public static native void removeFromFavorites(String bookId);
    public static native boolean isFavorite(String bookId);
    public static native List<String> getFavoriteBooks();
}
