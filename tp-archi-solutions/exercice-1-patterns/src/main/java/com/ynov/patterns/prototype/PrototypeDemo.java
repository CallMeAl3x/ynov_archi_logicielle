package com.ynov.patterns.prototype;

/**
 * Demonstrates the Prototype pattern by cloning books and
 * proving that clones are fully independent from originals.
 */
public class PrototypeDemo {

    public static void main(String[] args) {
        System.out.println("=== Prototype Pattern Demo ===\n");

        // --- PhysicalBook prototype ---
        PhysicalBook original = new PhysicalBook(
                "Effective Java", "Joshua Bloch",
                "978-0134685991", "B2-05", true);

        PhysicalBook clone = original.cloneBook();

        System.out.println("Original : " + original);
        System.out.println("Clone    : " + clone);
        System.out.println("Same ref?: " + (original == clone));   // false

        // Mutate the clone — original must not change
        clone.setLocation("C7-03");
        clone.setAvailable(false);

        System.out.println("\nAfter mutating clone:");
        System.out.println("Original location : " + original.getLocation());  // B2-05
        System.out.println("Clone location    : " + clone.getLocation());      // C7-03
        System.out.println("Original available: " + original.isAvailable());  // true
        System.out.println("Clone available   : " + clone.isAvailable());     // false

        // --- EBook prototype ---
        System.out.println();
        EBook ebook  = new EBook("Head First Design Patterns",
                                 "Eric Freeman", "978-0596007126",
                                 15.4, "PDF");
        EBook ebook2 = ebook.cloneBook();

        System.out.println("EBook original : " + ebook);
        System.out.println("EBook clone    : " + ebook2);
        System.out.println("Same ref?      : " + (ebook == ebook2));  // false

        ebook2.setFormat("EPUB");
        System.out.println("\nAfter changing clone format:");
        System.out.println("Original format: " + ebook.getFormat());   // PDF
        System.out.println("Clone format   : " + ebook2.getFormat());  // EPUB

        System.out.println("\n=== Prototype Pattern: DONE ===");
    }
}
