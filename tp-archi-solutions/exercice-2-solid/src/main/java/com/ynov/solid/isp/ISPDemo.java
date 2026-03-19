package com.ynov.solid.isp;

import com.ynov.solid.isp.solution.*;

/**
 * Demonstrates ISP: fat interface problem vs segregated interfaces solution.
 */
public class ISPDemo {

    public static void main(String[] args) {
        System.out.println("=== ISP Demo ===\n");

        System.out.println("--- PROBLEM: fat interface forces useless implementations ---");
        System.out.println("  PhysicalBook would have to provide download() and stream() that");
        System.out.println("  throw UnsupportedOperationException — ISP violation.");

        System.out.println();
        System.out.println("--- SOLUTION: segregated interfaces ---");

        PhysicalBook physical = new PhysicalBook("978-111", "Clean Code");
        EBook        ebook    = new EBook("978-222",         "Refactoring");
        AudioBook    audio    = new AudioBook("978-333",     "The Pragmatic Programmer");

        System.out.println("\n[PhysicalBook operations]");
        physical.borrow();
        physical.print();
        physical.returnItem();

        System.out.println("\n[EBook operations]");
        ebook.borrow();
        ebook.download();
        ebook.returnItem();

        System.out.println("\n[AudioBook operations]");
        audio.borrow();
        audio.stream();
        audio.returnItem();

        System.out.println("\n[Polymorphic via Borrowable interface]");
        Borrowable[] borrowables = { physical, ebook, audio };
        for (Borrowable b : borrowables) {
            System.out.println("  ISBN: " + b.getISBN());
            b.borrow();
        }

        System.out.println("\n=== ISP Demo: DONE ===");
    }
}
