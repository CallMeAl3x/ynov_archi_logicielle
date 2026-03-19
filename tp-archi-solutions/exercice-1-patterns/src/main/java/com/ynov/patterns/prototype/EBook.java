package com.ynov.patterns.prototype;

/**
 * A digital book available for download.
 * Implements BookPrototype to allow deep cloning.
 */
public class EBook implements BookPrototype {

    private String title;
    private String author;
    private String isbn;
    private double fileSize;   // in MB
    private String format;     // "PDF" or "EPUB"

    public EBook(String title, String author, String isbn,
                 double fileSize, String format) {
        this.title    = title;
        this.author   = author;
        this.isbn     = isbn;
        this.fileSize = fileSize;
        this.format   = format;
    }

    /**
     * Deep copy — all fields are value types or immutable Strings.
     */
    @Override
    public EBook cloneBook() {
        return new EBook(
            this.title,
            this.author,
            this.isbn,
            this.fileSize,
            this.format
        );
    }

    // --- Getters & Setters ---

    public String getTitle()      { return title; }
    public void   setTitle(String title) { this.title = title; }

    public String getAuthor()     { return author; }
    public void   setAuthor(String author) { this.author = author; }

    public String getIsbn()       { return isbn; }
    public void   setIsbn(String isbn) { this.isbn = isbn; }

    public double getFileSize()   { return fileSize; }
    public void   setFileSize(double fileSize) { this.fileSize = fileSize; }

    public String getFormat()     { return format; }
    public void   setFormat(String format) { this.format = format; }

    @Override
    public String toString() {
        return "EBook{title='" + title + "', author='" + author +
               "', isbn='" + isbn + "', fileSize=" + fileSize +
               "MB, format='" + format + "'}";
    }
}
