package service;

import model.Book;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface LibraryService {
    List<Book> getAllBooks();
    Optional<Book> getBookById(int id);
    Optional<Book> findBookByTitle(String title);

    List<Book> filterByGenre(String genre);
    List<Book> filterByAuthor(String author);
    List<Book> filterByPriceRange(double min, double max);
    List<Book> filterByLanguage(String language);
    List<Book> filterAvailableBooks();
    List<Book> filterByPublicationYear(int year);

    List<Book> sortByTitleAsc();
    List<Book> sortByTitleDesc();
    List<Book> sortByPriceAsc();
    List<Book> sortByPriceDesc();
    List<Book> sortByPublicationYear();

    Map<String, List<Book>> groupBooksByAuthor();
    Map<String, List<Book>> groupBooksByGenre();
    double getAverageBookPrice();
    Map<String, Long> getBookCountByGenre();

    Optional<Book> findMostExpensiveBook();
    Optional<Book> findCheapestBook();
    Optional<Book> findNewestBook();
    Optional<Book> findOldestBook();
    long getTotalBookCount();
    boolean isBookAvailable(String title);

    void addBook(Book book);
    boolean removeBook(int id);
    boolean updateBookAvailability(int id, boolean status);

    List<String> getDistinctAuthors();
    List<String> getDistinctGenres();
    void getBooksSummary();
}