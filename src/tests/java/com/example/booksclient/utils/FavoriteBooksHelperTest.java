package tests.com.example.booksclient;

import com.example.booksclient.utils.FavoriteBooksHelper;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FavoriteBooksHelperTest {

    @Test
    void testCombineJsonStrings_withValidJsonStrings() {
        // Arrange: Setup data
        String json1 = "{\"id\": \"1\", \"name\": \"Book1\"}";
        String json2 = "{\"id\": \"2\", \"name\": \"Book2\"}";
        var jsonList = Arrays.asList(json1, json2);

        // Act: Call the method
        String resultJson = FavoriteBooksHelper.combineJsonStrings(jsonList);

        // Assert: Verify the output
        String expected = "{\"items\":[{\"id\":\"1\",\"name\":\"Book1\"},{\"id\":\"2\",\"name\":\"Book2\"}]}";
        assertEquals(expected, resultJson, "Combined JSON strings did not match the expected output.");
    }

    @Test
    void testCombineJsonStrings_withEmptyInput() {
        // Arrange: Empty list
        List<String> jsonList = Arrays.asList();

        // Act: Call the method
        String resultJson = FavoriteBooksHelper.combineJsonStrings(jsonList);

        // Assert
        String expected = "{\"items\":[]}";
        assertEquals(expected, resultJson, "Empty input should produce an empty array.");
    }

    @Test
    void testCombineJsonStrings_withNullInput() {
        assertThrows(NullPointerException.class, () -> {
            FavoriteBooksHelper.combineJsonStrings(null);
        }, "Null input should throw NullPointerException");
    }
}