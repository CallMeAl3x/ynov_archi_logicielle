package com.ynov.solid.dip.solution;

/**
 * Low-level module: MySQL implementation of DatabaseRepository.
 * Depends on the abstraction — not on OrderService.
 */
public class MySQLDatabase implements DatabaseRepository {

    @Override
    public void save(String data) {
        System.out.println("[MySQLDatabase] Saving: " + data);
    }

}
