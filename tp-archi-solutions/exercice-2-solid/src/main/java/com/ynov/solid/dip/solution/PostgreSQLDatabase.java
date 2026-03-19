package com.ynov.solid.dip.solution;

/**
 * Low-level module: PostgreSQL implementation — swap in without changing OrderService.
 */
public class PostgreSQLDatabase implements DatabaseRepository {

    @Override
    public void save(String data) {
        System.out.println("[PostgreSQLDatabase] Persisting: " + data);
    }

}
