package com.ynov.solid.dip.solution;

/**
 * SOLUTION: High-level abstraction for data persistence.
 * Both OrderService and concrete DB classes depend on this interface.
 */
@FunctionalInterface
public interface DatabaseRepository {
    void save(String data);
}
