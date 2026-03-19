package com.ynov.library.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateBookDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    @NotBlank(message = "ISBN is required")
    private String isbn;

    private Integer publishYear;

    private String genre;

    // No-arg constructor
    public CreateBookDTO() {}

    // All-arg constructor
    public CreateBookDTO(String title, String author, String isbn, Integer publishYear, String genre) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishYear = publishYear;
        this.genre = genre;
    }

    // Getters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public Integer getPublishYear() { return publishYear; }
    public String getGenre() { return genre; }

    // Setters
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setPublishYear(Integer publishYear) { this.publishYear = publishYear; }
    public void setGenre(String genre) { this.genre = genre; }
}
