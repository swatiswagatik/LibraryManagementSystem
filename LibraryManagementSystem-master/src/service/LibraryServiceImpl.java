package service;

import dao.LibraryDao;
import dao.LibraryDaoImpl;
import model.Book;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Business logic using Stream API and Optionals; delegates to DAO implementation.
 */
public class LibraryServiceImpl implements LibraryService {
    private static final Logger logger = Logger.getLogger(LibraryServiceImpl.class.getName());
    private final LibraryDao dao;

    public LibraryServiceImpl() {
        this.dao = new LibraryDaoImpl();
        logger.info("LibraryServiceImpl initialized.");
    }

    @Override
    public List<Book> getAllBooks() {
        logger.info("Service: getAllBooks");
        List<Book> list = dao.getAllBooks();
        logger.info("Books returned: " + list.size());
        return list;
    }

    @Override
    public Optional<Book> getBookById(int id) {
        logger.info("Service: getBookById " + id);
        Optional<Book> b = dao.getBookById(id);
        if (!b.isPresent()) logger.warning("Book not found for id: " + id);
        return b;
    }

    @Override
    public Optional<Book> findBookByTitle(String title) {
        logger.info("Service: findBookByTitle " + title);
        Optional<Book> b = dao.findBookByTitle(title);
        if (!b.isPresent()) logger.warning("Book not found for title: " + title);
        return b;
    }

    @Override
    public List<Book> filterByGenre(String genre) {
        logger.info("Service: filterByGenre " + genre);
        List<Book> list = dao.getBooksByGenre(genre);
        logger.info("Found: " + list.size());
        return list;
    }

    @Override
    public List<Book> filterByAuthor(String author) {
        logger.info("Service: filterByAuthor " + author);
        List<Book> list = dao.getBooksByAuthor(author);
        logger.info("Found: " + list.size());
        return list;
    }

    @Override
    public List<Book> filterByPriceRange(double min, double max) {
        logger.info("Service: filterByPriceRange " + min + " - " + max);
        return dao.getBooksByPriceRange(min, max);
    }

    @Override
    public List<Book> filterByLanguage(String language) {
        logger.info("Service: filterByLanguage " + language);
        return dao.getBooksByLanguage(language);
    }

    @Override
    public List<Book> filterAvailableBooks() {
        logger.info("Service: filterAvailableBooks");
        return dao.getAvailableBooks();
    }

    @Override
    public List<Book> filterByPublicationYear(int year) {
        logger.info("Service: filterByPublicationYear " + year);
        return dao.getBooksByPublicationYear(year);
    }

    @Override
    public List<Book> sortByTitleAsc() {
        logger.info("Service: sortByTitleAsc");
        return dao.getBooksSortedByTitleAsc();
    }

    @Override
    public List<Book> sortByTitleDesc() {
        logger.info("Service: sortByTitleDesc");
        return dao.getBooksSortedByTitleDesc();
    }

    @Override
    public List<Book> sortByPriceAsc() {
        logger.info("Service: sortByPriceAsc");
        return dao.getBooksSortedByPriceAsc();
    }

    @Override
    public List<Book> sortByPriceDesc() {
        logger.info("Service: sortByPriceDesc");
        return dao.getBooksSortedByPriceDesc();
    }

    @Override
    public List<Book> sortByPublicationYear() {
        logger.info("Service: sortByPublicationYear");
        return dao.getBooksSortedByPublicationYear();
    }

    @Override
    public Map<String, List<Book>> groupBooksByAuthor() {
        logger.info("Service: groupBooksByAuthor");
        Map<String, List<Book>> map = dao.getBooksGroupedByAuthor();
        logger.info("Groups: " + map.keySet().size());
        return map;
    }

    @Override
    public Map<String, List<Book>> groupBooksByGenre() {
        logger.info("Service: groupBooksByGenre");
        return dao.getBooksGroupedByGenre();
    }

    @Override
    public double getAverageBookPrice() {
        logger.info("Service: getAverageBookPrice");
        double avg = dao.calculateAverageBookPrice();
        logger.info("Average price: " + avg);
        return avg;
    }

    @Override
    public java.util.Map<String, Long> getBookCountByGenre() {
        logger.info("Service: getBookCountByGenre");
        return dao.getBookCountByGenre();
    }

    @Override
    public Optional<Book> findMostExpensiveBook() {
        logger.info("Service: findMostExpensiveBook");
        return dao.getMostExpensiveBook();
    }

    @Override
    public Optional<Book> findCheapestBook() {
        logger.info("Service: findCheapestBook");
        return dao.getCheapestBook();
    }

    @Override
    public Optional<Book> findNewestBook() {
        logger.info("Service: findNewestBook");
        return dao.getNewestBook();
    }

    @Override
    public Optional<Book> findOldestBook() {
        logger.info("Service: findOldestBook");
        return dao.getOldestBook();
    }

    @Override
    public long getTotalBookCount() {
        logger.info("Service: getTotalBookCount");
        return dao.getTotalBookCount();
    }

    @Override
    public boolean isBookAvailable(String title) {
        logger.info("Service: isBookAvailable " + title);
        return dao.checkBookAvailability(title);
    }

    @Override
    public void addBook(Book book) {
        logger.info("Service: addBook " + book.getTitle());
        dao.addBook(book);
    }

    @Override
    public boolean removeBook(int id) {
        logger.info("Service: removeBook " + id);
        return dao.removeBook(id);
    }

    @Override
    public boolean updateBookAvailability(int id, boolean status) {
        logger.info("Service: updateBookAvailability " + id + " -> " + status);
        return dao.updateBookAvailability(id, status);
    }

    @Override
    public List<String> getDistinctAuthors() {
        return dao.getDistinctAuthors();
    }

    @Override
    public List<String> getDistinctGenres() {
        return dao.getDistinctGenres();
    }

    @Override
    public void getBooksSummary() {
        dao.printBooksSummary();
    }
}