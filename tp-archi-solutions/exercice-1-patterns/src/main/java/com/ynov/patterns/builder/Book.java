package com.ynov.patterns.builder;

/**
 * Book class using the Builder pattern.
 * Only title and author are required; all other fields are optional.
 */
public class Book {

    // Required fields
    private final String title;
    private final String author;

    // Optional fields
    private final String isbn;
    private final Integer year;
    private final String genre;
    private final String description;

    // Private constructor — only the Builder can instantiate
    private Book(Builder builder) {
        this.title       = builder.title;
        this.author      = builder.author;
        this.isbn        = builder.isbn;
        this.year        = builder.year;
        this.genre       = builder.genre;
        this.description = builder.description;
    }

    // --- Getters ---

    public String getTitle()       { return title; }
    public String getAuthor()      { return author; }
    public String getIsbn()        { return isbn; }
    public Integer getYear()       { return year; }
    public String getGenre()       { return genre; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return "Book{" +
               "title='"       + title       + '\'' +
               ", author='"    + author      + '\'' +
               ", isbn='"      + isbn        + '\'' +
               ", year="       + year        +
               ", genre='"     + genre       + '\'' +
               ", description='" + description + '\'' +
               '}';
    }

    // =========================================================
    // Static inner Builder class
    // =========================================================
    public static class Builder {

        // Required
        private final String title;
        private final String author;

        // Optional — default values
        private String  isbn        = null;
        private Integer year        = null;
        private String  genre       = null;
        private String  description = null;

        /**
         * Constructor requires the mandatory fields.
         */
        public Builder(String title, String author) {
            if (title == null || title.isBlank()) {
                throw new IllegalArgumentException("Title is required");
            }
            if (author == null || author.isBlank()) {
                throw new IllegalArgumentException("Author is required");
            }
            this.title  = title;
            this.author = author;
        }

        public Builder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Builder genre(String genre) {
            this.genre = genre;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
}
