package com.ynov.solid.isp.problem;

/**
 * PROBLEM: Fat interface — forces every implementation to provide
 * ALL methods, even those that don't make sense for the type.
 *
 * A PhysicalBook cannot be streamed; an AudioBook cannot be printed.
 * All implementations will throw UnsupportedOperationException for
 * irrelevant methods — ISP violation.
 */
public interface LibraryItem {

    void   borrow();
    void   returnItem();
    void   download();     // meaningless for PhysicalBook
    void   stream();       // meaningless for PhysicalBook and EBook
    void   print();        // meaningless for AudioBook
    String getISBN();
}
