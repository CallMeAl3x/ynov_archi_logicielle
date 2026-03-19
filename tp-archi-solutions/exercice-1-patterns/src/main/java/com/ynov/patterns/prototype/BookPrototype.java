package com.ynov.patterns.prototype;

/**
 * Prototype interface for book objects.
 * Implementors must provide a deep-copy cloning method.
 */
public interface BookPrototype {

    /**
     * Creates and returns a deep copy of this book.
     *
     * @return a new independent instance with the same data
     */
    BookPrototype cloneBook();
}
