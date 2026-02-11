package dao;

import datasource.LibraryDataSource;
import model.Book;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class LibraryDaoImpl implements LibraryDao {
    private static final Logger logger = Logger.getLogger(LibraryDaoImpl.class.getName());
    private final List<Book> books;

    public LibraryDaoImpl() {
        this.books = LibraryDataSource.getBooks();
        logger.info("LibraryDaoImpl initialized. Books loaded: " + books.size());
    }

    @Override
    public List<Book> getAllBooks() {
        logger.info("Fetching all books. Count: " + books.size());
        return new ArrayList<>(books);
    }

    @Override
    public Optional<Book> getBookById(int id) {
        logger.info("Searching book by ID: " + id);
        return books.stream().filter(b -> b.getId() == id).findFirst();
    }

    @Override
    public Optional<Book> findBookByTitle(String title) {
        logger.info("Searching book by title: " + title);
        return books.stream().filter(b -> b.getTitle().equalsIgnoreCase(title)).findFirst();
    }

    @Override
    public List<Book> getBooksByGenre(String genre) {
        logger.info("Filtering books by genre: " + genre);
        return books.stream().filter(b -> b.getGenre().equalsIgnoreCase(genre)).collect(Collectors.toList());
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        logger.info("Filtering books by author: " + author);
        return books.stream().filter(b -> b.getAuthor().equalsIgnoreCase(author)).collect(Collectors.toList());
    }

    @Override
    public List<Book> getBooksByPriceRange(double min, double max) {
        logger.info(String.format("Filtering books by price range: %.2f - %.2f", min, max));
        return books.stream().filter(b -> b.getPrice() >= min && b.getPrice() <= max).collect(Collectors.toList());
    }

    @Override
    public List<Book> getBooksByLanguage(String language) {
        logger.info("Filtering books by language: " + language);
        return books.stream().filter(b -> b.getLanguage().equalsIgnoreCase(language)).collect(Collectors.toList());
    }

    @Override
    public List<Book> getAvailableBooks() {
        logger.info("Filtering available books");
        return books.stream().filter(Book::isAvailable).collect(Collectors.toList());
    }

    @Override
    public List<Book> getBooksByPublicationYear(int year) {
        logger.info("Filtering books by publication year: " + year);
        return books.stream().filter(b -> b.getPublicationYear() == year).collect(Collectors.toList());
    }

    @Override
    public List<Book> getBooksSortedByTitleAsc() {
        logger.info("Sorting books by title asc");
        return books.stream().sorted(Comparator.comparing(Book::getTitle)).collect(Collectors.toList());
    }

    @Override
    public List<Book> getBooksSortedByTitleDesc() {
        logger.info("Sorting books by title desc");
        return books.stream().sorted(Comparator.comparing(Book::getTitle).reversed()).collect(Collectors.toList());
    }

    @Override
    public List<Book> getBooksSortedByPriceAsc() {
        logger.info("Sorting books by price asc");
        return books.stream().sorted(Comparator.comparingDouble(Book::getPrice)).collect(Collectors.toList());
    }

    @Override
    public List<Book> getBooksSortedByPriceDesc() {
        logger.info("Sorting books by price desc");
        return books.stream().sorted(Comparator.comparingDouble(Book::getPrice).reversed()).collect(Collectors.toList());
    }

    @Override
    public List<Book> getBooksSortedByPublicationYear() {
        logger.info("Sorting books by publication year asc");
        return books.stream().sorted(Comparator.comparingInt(Book::getPublicationYear)).collect(Collectors.toList());
    }

    @Override
    public Map<String, List<Book>> getBooksGroupedByAuthor() {
        logger.info("Grouping books by author");
        return books.stream().collect(Collectors.groupingBy(Book::getAuthor));
    }

    @Override
    public Map<String, List<Book>> getBooksGroupedByGenre() {
        logger.info("Grouping books by genre");
        return books.stream().collect(Collectors.groupingBy(Book::getGenre));
    }

    @Override
    public double calculateAverageBookPrice() {
        logger.info("Calculating average book price");
        return books.stream().mapToDouble(Book::getPrice).average().orElse(0.0);
    }

    @Override
    public Map<String, Long> getBookCountByGenre() {
        logger.info("Counting books by genre");
        return books.stream().collect(Collectors.groupingBy(Book::getGenre, Collectors.counting()));
    }

    @Override
    public Optional<Book> getMostExpensiveBook() {
        logger.info("Finding most expensive book");
        return books.stream().max(Comparator.comparingDouble(Book::getPrice));
    }

    @Override
    public Optional<Book> getCheapestBook() {
        logger.info("Finding cheapest book");
        return books.stream().min(Comparator.comparingDouble(Book::getPrice));
    }

    @Override
    public Optional<Book> getNewestBook() {
        logger.info("Finding newest book");
        return books.stream().max(Comparator.comparingInt(Book::getPublicationYear));
    }

    @Override
    public Optional<Book> getOldestBook() {
        logger.info("Finding oldest book");
        return books.stream().min(Comparator.comparingInt(Book::getPublicationYear));
    }

    @Override
    public long getTotalBookCount() {
        logger.info("Getting total book count");
        return books.size();
    }

    @Override
    public boolean checkBookAvailability(String title) {
        logger.info("Checking availability for: " + title);
        return books.stream().filter(b -> b.getTitle().equalsIgnoreCase(title)).findFirst().map(Book::isAvailable).orElse(false);
    }

    @Override
    public void addBook(Book book) {
        logger.info("Adding book: " + book.getTitle());
        books.add(book);
        logger.info("Book added. New count: " + books.size());
    }

    @Override
    public boolean removeBook(int id) {
        logger.info("Removing book by id: " + id);
        Optional<Book> book = getBookById(id);
        if (book.isPresent()) {
            boolean removed = books.remove(book.get());
            logger.info("Removal successful: " + removed);
            return removed;
        } else {
            logger.warning("Book with id " + id + " not found.");
            return false;
        }
    }

    @Override
    public boolean updateBookAvailability(int id, boolean status) {
        logger.info("Updating availability for id: " + id + " to " + status);
        Optional<Book> book = getBookById(id);
        if (book.isPresent()) {
            book.get().setAvailable(status);
            logger.info("Availability updated.");
            return true;
        } else {
            logger.warning("Book with id " + id + " not found for update.");
            return false;
        }
    }

    @Override
    public List<String> getDistinctAuthors() {
        logger.info("Getting distinct authors");
        return books.stream().map(Book::getAuthor).distinct().collect(Collectors.toList());
    }

    @Override
    public List<String> getDistinctGenres() {
        logger.info("Getting distinct genres");
        return books.stream().map(Book::getGenre).distinct().collect(Collectors.toList());
    }

    @Override
    public void printBooksSummary() {
        logger.info("Generating books summary");
        System.out.println("Total books: " + getTotalBookCount());
        System.out.println("Average price: " + calculateAverageBookPrice());
        getBookCountByGenre().forEach((g, c) -> System.out.println(g + ": " + c));
    }
}