// package main;

import model.Book;
import service.LibraryService;
import service.LibraryServiceImpl;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Main console application with switch-case menu.
 */
public class LibraryApp {
    private static final Logger logger = Logger.getLogger(LibraryApp.class.getName());
    private static final Scanner scanner = new Scanner(System.in);
    private static final LibraryService service = new LibraryServiceImpl();

    public static void main(String[] args) {
        logger.info("Library Application started.");
        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine();
            int choice = -1;
            try {
                choice = Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    printBooks(service.getAllBooks());
                    break;
                case 2:
                    System.out.print("Enter ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    service.getBookById(id).ifPresentOrElse(
                            b -> System.out.println(b),
                            () -> System.out.println("Book not found")
                    );
                    break;
                case 3:
                    System.out.print("Enter title: ");
                    service.findBookByTitle(scanner.nextLine()).ifPresentOrElse(
                            b -> System.out.println(b),
                            () -> System.out.println("Book not found")
                    );
                    break;
                case 4:
                    System.out.print("Enter genre: ");
                    printBooks(service.filterByGenre(scanner.nextLine()));
                    break;
                case 5:
                    System.out.print("Enter author: ");
                    printBooks(service.filterByAuthor(scanner.nextLine()));
                    break;
                case 6:
                    System.out.print("Enter language: ");
                    printBooks(service.filterByLanguage(scanner.nextLine()));
                    break;
                case 7:
                    System.out.print("Enter publication year: ");
                    int year = Integer.parseInt(scanner.nextLine());
                    printBooks(service.filterByPublicationYear(year));
                    break;
                case 8:
                    printBooks(service.filterAvailableBooks());
                    break;
                case 9:
                    printBooks(service.sortByTitleAsc());
                    break;
                case 10:
                    printBooks(service.sortByTitleDesc());
                    break;
                case 11:
                    printBooks(service.sortByPriceAsc());
                    break;
                case 12:
                    printBooks(service.sortByPriceDesc());
                    break;
                case 13:
                    printBooks(service.sortByPublicationYear());
                    break;
                case 14:
                    printGrouped(service.groupBooksByAuthor());
                    break;
                case 15:
                    printGrouped(service.groupBooksByGenre());
                    break;
                case 16:
                    System.out.println("Average book price: " + service.getAverageBookPrice());
                    break;
                case 17:
                    service.getBookCountByGenre().forEach((k,v) -> System.out.println(k + " -> " + v));
                    break;
                case 18:
                    service.findMostExpensiveBook().ifPresent(System.out::println);
                    break;
                case 19:
                    service.findCheapestBook().ifPresent(System.out::println);
                    break;
                case 20:
                    service.findNewestBook().ifPresent(System.out::println);
                    break;
                case 21:
                    service.findOldestBook().ifPresent(System.out::println);
                    break;
                case 22:
                    System.out.print("Enter title to check availability: ");
                    System.out.println("Available? " + service.isBookAvailable(scanner.nextLine()));
                    break;
                case 23:
                    System.out.println("Total books: " + service.getTotalBookCount());
                    break;
                case 24:
                    Book b = readBookFromInput();
                    service.addBook(b);
                    System.out.println("Book added.");
                    break;
                case 25:
                    System.out.print("Enter ID to remove: ");
                    int rid = Integer.parseInt(scanner.nextLine());
                    System.out.println("Removed? " + service.removeBook(rid));
                    break;
                case 26:
                    System.out.print("Enter ID to update availability: ");
                    int uid = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter availability (true/false): ");
                    boolean st = Boolean.parseBoolean(scanner.nextLine());
                    System.out.println("Updated? " + service.updateBookAvailability(uid, st));
                    break;
                case 27:
                    service.getDistinctAuthors().forEach(System.out::println);
                    break;
                case 28:
                    service.getDistinctGenres().forEach(System.out::println);
                    break;
                case 29:
                    service.getBooksSummary();
                    break;
                case 30:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Unknown option.");
            }
        }
        logger.info("Library Application finished.");
    }

    private static void printMenu() {
        System.out.println("\n================ LIBRARY MANAGEMENT SYSTEM ================\n");
        System.out.println("1. View all books");
        System.out.println("2. Search book by ID");
        System.out.println("3. Search book by title");
        System.out.println("4. Filter books by genre");
        System.out.println("5. Filter books by author");
        System.out.println("6. Filter books by language");
        System.out.println("7. Filter books by publication year");
        System.out.println("8. View available books");
        System.out.println("\n--- Sorting Operations ---");
        System.out.println("9. Sort books by title (A-Z)");
        System.out.println("10. Sort books by title (Z-A)");
        System.out.println("11. Sort books by price (Low -> High)");
        System.out.println("12. Sort books by price (High -> Low)");
        System.out.println("13. Sort books by publication year");
        System.out.println("\n--- Grouping and Statistics ---");
        System.out.println("14. Group books by author");
        System.out.println("15. Group books by genre");
        System.out.println("16. Get average book price");
        System.out.println("17. Get book count by genre");
        System.out.println("\n--- Analytical Operations ---");
        System.out.println("18. Find most expensive book");
        System.out.println("19. Find cheapest book");
        System.out.println("20. Find newest book");
        System.out.println("21. Find oldest book");
        System.out.println("22. Check if a book is available by title");
        System.out.println("23. Get total number of books");
        System.out.println("\n--- Book Management ---");
        System.out.println("24. Add a new book");
        System.out.println("25. Remove a book by ID");
        System.out.println("26. Update book availability");
        System.out.println("\n--- Utility and Summary ---");
        System.out.println("27. View distinct authors");
        System.out.println("28. View distinct genres");
        System.out.println("29. Show library summary");
        System.out.println("\n30. Exit application\n");
    }

    private static void printBooks(List<Book> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("No books found.");
            return;
        }
        list.forEach(System.out::println);
    }

    private static void printGrouped(Map<String, List<Book>> grouped) {
        if (grouped == null || grouped.isEmpty()) {
            System.out.println("No groups found.");
            return;
        }
        grouped.forEach((k, v) -> {
            System.out.println("----- " + k + " (" + v.size() + ") -----");
            v.forEach(System.out::println);
        });
    }

    private static Book readBookFromInput() {
        System.out.print("Enter id: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter publication year: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter publisher: ");
        String publisher = scanner.nextLine();
        System.out.print("Enter language: ");
        String language = scanner.nextLine();
        System.out.print("Enter pages: ");
        int pages = Integer.parseInt(scanner.nextLine());
        System.out.print("Is available (true/false): ");
        boolean available = Boolean.parseBoolean(scanner.nextLine());
        return new Book(id, title, author, genre, price, year, publisher, language, pages, available);
    }
}