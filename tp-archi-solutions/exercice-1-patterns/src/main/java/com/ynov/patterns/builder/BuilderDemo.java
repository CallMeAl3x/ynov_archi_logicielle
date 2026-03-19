package com.ynov.patterns.builder;

/**
 * Demonstrates the Builder pattern with three Book creation examples.
 */
public class BuilderDemo {

    public static void main(String[] args) {
        System.out.println("=== Builder Pattern Demo ===\n");

        // --- Example 1: Complete book with all fields ---
        Book completeBook = new Book.Builder("Clean Code", "Robert C. Martin")
                .isbn("978-0132350884")
                .year(2008)
                .genre("Software Engineering")
                .description("A handbook of agile software craftsmanship.")
                .build();

        System.out.println("1. Complete Book:");
        System.out.println("   " + completeBook);

        // --- Example 2: Partial book (missing isbn and description) ---
        Book partialBook = new Book.Builder("Design Patterns", "Gang of Four")
                .year(1994)
                .genre("Software Architecture")
                .build();

        System.out.println("\n2. Partial Book (no ISBN or description):");
        System.out.println("   " + partialBook);
        System.out.println("   ISBN is null: " + (partialBook.getIsbn() == null));

        // --- Example 3: Minimal book (only required fields) ---
        Book minimalBook = new Book.Builder("The Pragmatic Programmer", "Andrew Hunt")
                .build();

        System.out.println("\n3. Minimal Book (title + author only):");
        System.out.println("   Title:  " + minimalBook.getTitle());
        System.out.println("   Author: " + minimalBook.getAuthor());
        System.out.println("   Year:   " + minimalBook.getYear());   // null
        System.out.println("   Genre:  " + minimalBook.getGenre());  // null

        System.out.println("\n=== Builder Pattern: DONE ===");
    }
}
