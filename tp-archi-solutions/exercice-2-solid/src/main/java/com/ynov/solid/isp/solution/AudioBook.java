package com.ynov.solid.isp.solution;

/**
 * AudioBook: can be borrowed and streamed.
 * Does NOT implement Downloadable or Printable.
 */
public class AudioBook implements Borrowable, Streamable {

    private final String isbn;
    private final String title;

    public AudioBook(String isbn, String title) {
        this.isbn  = isbn;
        this.title = title;
    }

    @Override public void   borrow()     { System.out.println("[AudioBook] '" + title + "' checked out."); }
    @Override public void   returnItem() { System.out.println("[AudioBook] '" + title + "' checked in."); }
    @Override public String getISBN()    { return isbn; }
    @Override public void   stream()     { System.out.println("[AudioBook] Streaming '" + title + "'..."); }
}
