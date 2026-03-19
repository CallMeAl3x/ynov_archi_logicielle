package com.ynov.patterns.prototype;

/**
 * A physical book that can be shelved and borrowed.
 * Implements BookPrototype to allow deep cloning.
 */
public class PhysicalBook implements BookPrototype {

    private String  title;
    private String  author;
    private String  isbn;
    private String  location;   // shelf reference, e.g. "A3-12"
    private boolean available;

    public PhysicalBook(String title, String author, String isbn,
                        String location, boolean available) {
        this.title     = title;
        this.author    = author;
        this.isbn      = isbn;
        this.location  = location;
        this.available = available;
    }

    /**
     * Deep copy — all fields are primitives/Strings so a field-by-field
     * copy gives full independence.
     */
    @Override
    public PhysicalBook cloneBook() {
        return new PhysicalBook(
            this.title,
            this.author,
            this.isbn,
            this.location,
            this.available
        );
    }

    // --- Getters & Setters ---

    public String getTitle()      { return title; }
    public void   setTitle(String title) { this.title = title; }

    public String getAuthor()     { return author; }
    public void   setAuthor(String author) { this.author = author; }

    public String getIsbn()       { return isbn; }
    public void   setIsbn(String isbn) { this.isbn = isbn; }

    public String getLocation()   { return location; }
    public void   setLocation(String location) { this.location = location; }

    public boolean isAvailable()  { return available; }
    public void    setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return "PhysicalBook{title='" + title + "', author='" + author +
               "', isbn='" + isbn + "', location='" + location +
               "', available=" + available + '}';
    }
}
