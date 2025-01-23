package tests.com.example.booksclient;

import com.example.booksclient.models.domain.Book;
import com.example.booksclient.models.parsers.BooksParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BooksParserTest {

    @Test
    void parseBooksJson_ValidJsonWithBooks_ShouldReturnBookList() {
        // Arrange
        String json = """
                {
                  "items": [
                    {"id": "1", "volumeInfo": {"title": "Book 1"}},
                    {"id": "2", "volumeInfo": {"title": "Book 2"}}
                  ]
                }
                """;

        // Act
        List<Book> books = BooksParser.parseBooksJson(json);

        // Assert
        assertNotNull(books, "Books list should not be null");
        assertEquals(2, books.size(), "Books list size should match the number of items in JSON");
        assertEquals("Book 1", books.get(0).getTitle(), "First book title should match");
        assertEquals("Book 2", books.get(1).getTitle(), "Second book title should match");
    }

    @Test
    void parseBooksJson_ValidJsonEmptyBooks_ShouldReturnEmptyList() {
        //Arrange
        String json = """
                {
                  "items": []
                }
                """;

        // Act
        List<Book> books = BooksParser.parseBooksJson(json);

        // Assert
        assertNotNull(books, "Books list should not be null");
        assertEquals(0, books.size(), "Books list should be empty if JSON has no items");
    }

    @Test
    void parseBooksJson_NullInput_ShouldHandleGracefully() {
        // Arrange: Null input
        String json = null;

        // Act
        List<Book> books = BooksParser.parseBooksJson(json);

        // Assert
        // Null input should result in an empty list, not a NullPointerException
        assertNotNull(books, "Books list should not be null for null input");
        assertEquals(0, books.size(), "Books list should be empty for null input");
    }

    @Test
    void parseBooksJson_InvalidJson_ShouldReturnEmptyListAndHandleException() {
        // Arrange: Invalid JSON
        String json = """
                this is not valid JSON
                """;

        // Act
        List<Book> books = BooksParser.parseBooksJson(json);

        // Assert
        // Invalid JSON should result in an empty list and not throw an exception
        assertNotNull(books, "Books list should not be null for invalid JSON");
        assertEquals(0, books.size(), "Books list should be empty for invalid JSON");
    }

    @Test
    void parseBooksJson_JsonWithoutItems_ShouldReturnEmptyList() {
        // Arrange: JSON without "items" field
        String json = """
                {
                  "randomField": "value"
                }
                """;

        // Act
        List<Book> books = BooksParser.parseBooksJson(json);

        // Assert
        assertNotNull(books, "Books list should not be null for JSON without items field");
        assertEquals(0, books.size(), "Books list should be empty for JSON without items field");
    }
}