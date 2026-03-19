package com.ynov.library.dto;

public class BookDTO {
    private Long    id;
    private String  title;
    private String  author;
    private String  isbn;
    private Integer publishYear;
    private String  genre;
    private boolean available;

    // No-arg constructor
    public BookDTO() {}

    // All-arg constructor
    public BookDTO(Long id, String title, String author, String isbn, Integer publishYear, String genre, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishYear = publishYear;
        this.genre = genre;
        this.available = available;
    }

    // Getters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public Integer getPublishYear() { return publishYear; }
    public String getGenre() { return genre; }
    public boolean isAvailable() { return available; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setPublishYear(Integer publishYear) { this.publishYear = publishYear; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setAvailable(boolean available) { this.available = available; }

    // Static Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private String title;
        private String author;
        private String isbn;
        private Integer publishYear;
        private String genre;
        private boolean available;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder title(String title) { this.title = title; return this; }
        public Builder author(String author) { this.author = author; return this; }
        public Builder isbn(String isbn) { this.isbn = isbn; return this; }
        public Builder publishYear(Integer publishYear) { this.publishYear = publishYear; return this; }
        public Builder genre(String genre) { this.genre = genre; return this; }
        public Builder available(boolean available) { this.available = available; return this; }

        public BookDTO build() {
            return new BookDTO(id, title, author, isbn, publishYear, genre, available);
        }
    }
}
