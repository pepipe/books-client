# BOOKS Client
## Test description - Book Store
Book Store consists on showing a simple 2-column list of available books about mobile development.
Using google’s api for books, the app should fetch and display the Thumbnail of a few books at a time and load more as the user scroll’s through the list.

- [Rest API](https://developers.google.com/books/docs/v1/getting_started#REST)
- [Example API Call](https://www.googleapis.com/books/v1/volumes?q=ios&maxResults=20&startIndex=0)

The list should also have a button to filter/show only books that the user has set as favorite.

When the user clicks on one of the books, the app should present a detailed view displaying the most relevant information of the book: Title, Author, Description and, if available, a Buy link.

In the detail view, the user can also favorite or “unfavorite” a book. This option should be stored locally so it persists through each app usage.

Clicking on the Buy link should open the link on safari/chrome.

This app must be build fallowing this rules:
1. All the API integrations must be built using C++.
    1. The app should call this api method and receive a callback with the API result (a json object or
an error).
2. An APP built using Objective-c/Java that import and use those C++ class.

It is very important show your experience with testing;

Nice to have: Favorites in local storage using C++

## Project Setup - Windows
1. I used IntelliJ IDE with Oracle OpenJDK 23, this is a JavaFX project. If you use another ide you'll need to install open jdk manually.
2. Then need to install [JavaFX](https://gluonhq.com/products/javafx/)
3. Create a JAVA_HOME and JAVAFX_HOME env variables that point to the sdks.
4. Add to PATH %JAVA_HOME%\bin and %JAVAFX_HOME%\lib
5. Install git for windows and add to path mingw64 binary folder, eg `C:\Program Files\Git\mingw64\bin`
6. To create the artifacts you need to add JavaFX bin to project. In IntelliJ this is done by going to File->Project Structure->Artifacts 
   1. Add file and go to JavaFX_home/bin and select all files.
7. I had problems with gson not being in that list and need to add it manually.
