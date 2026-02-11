package model;

public class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private double price;
    private int publicationYear;
    private String publisher;
    private String language;
    private int pages;
    private boolean available;

    public Book(int id, String title, String author, String genre, double price,
                int publicationYear, String publisher, String language, int pages, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.language = language;
        this.pages = pages;
        this.available = available;
    }

    // Getters and setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public double getPrice() { return price; }
    public int getPublicationYear() { return publicationYear; }
    public String getPublisher() { return publisher; }
    public String getLanguage() { return language; }
    public int getPages() { return pages; }
    public boolean isAvailable() { return available; }

    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return String.format("Book:[ id=%d, title='%s', author='%s', genre='%s', price=%.2f, year=%d, publisher='%s', language='%s', pages=%d, available=%s ] ",
                id, title, author, genre, price, publicationYear, publisher, language, pages, available);
    }
}