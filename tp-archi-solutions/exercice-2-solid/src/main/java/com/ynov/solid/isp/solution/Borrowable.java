package com.ynov.solid.isp.solution;

/** Segregated interface for items that can be physically borrowed. */
public interface Borrowable {
    void   borrow();
    void   returnItem();
    String getISBN();
}
