package dao;

import model.Book;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface LibraryDao {
    List<Book> getAllBooks();
    Optional<Book> getBookById(int id);
    Optional<Book> findBookByTitle(String title);

    List<Book> getBooksByGenre(String genre);
    List<Book> getBooksByAuthor(String author);
    List<Book> getBooksByPriceRange(double min, double max);
    List<Book> getBooksByLanguage(String language);
    List<Book> getAvailableBooks();
    List<Book> getBooksByPublicationYear(int year);

    List<Book> getBooksSortedByTitleAsc();
    List<Book> getBooksSortedByTitleDesc();
    List<Book> getBooksSortedByPriceAsc();
    List<Book> getBooksSortedByPriceDesc();
    List<Book> getBooksSortedByPublicationYear();

    Map<String, java.util.List<Book>> getBooksGroupedByAuthor();
    Map<String, java.util.List<Book>> getBooksGroupedByGenre();

    double calculateAverageBookPrice();
    Map<String, Long> getBookCountByGenre();

    Optional<Book> getMostExpensiveBook();
    Optional<Book> getCheapestBook();
    Optional<Book> getNewestBook();
    Optional<Book> getOldestBook();
    long getTotalBookCount();
    boolean checkBookAvailability(String title);

    void addBook(Book book);
    boolean removeBook(int id);
    boolean updateBookAvailability(int id, boolean status);

    java.util.List<String> getDistinctAuthors();
    java.util.List<String> getDistinctGenres();
    void printBooksSummary();
}