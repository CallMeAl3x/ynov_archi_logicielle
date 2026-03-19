package com.ynov.solid.isp.solution;

/**
 * Physical book: can be borrowed and printed.
 * Does NOT implement Downloadable or Streamable — no unused methods.
 */
public class PhysicalBook implements Borrowable, Printable {

    private final String isbn;
    private final String title;

    public PhysicalBook(String isbn, String title) {
        this.isbn  = isbn;
        this.title = title;
    }

    @Override public void   borrow()     { System.out.println("[PhysicalBook] '" + title + "' borrowed."); }
    @Override public void   returnItem() { System.out.println("[PhysicalBook] '" + title + "' returned."); }
    @Override public String getISBN()    { return isbn; }
    @Override public void   print()      { System.out.println("[PhysicalBook] Printing '" + title + "'..."); }
}
