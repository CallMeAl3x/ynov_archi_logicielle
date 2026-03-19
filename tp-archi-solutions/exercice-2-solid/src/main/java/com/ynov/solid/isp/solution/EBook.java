package com.ynov.solid.isp.solution;

/**
 * EBook: can be borrowed and downloaded.
 * Does NOT implement Streamable or Printable.
 */
public class EBook implements Borrowable, Downloadable {

    private final String isbn;
    private final String title;

    public EBook(String isbn, String title) {
        this.isbn  = isbn;
        this.title = title;
    }

    @Override public void   borrow()     { System.out.println("[EBook] '" + title + "' loan started."); }
    @Override public void   returnItem() { System.out.println("[EBook] '" + title + "' loan returned."); }
    @Override public String getISBN()    { return isbn; }
    @Override public void   download()   { System.out.println("[EBook] Downloading '" + title + "'..."); }
}
