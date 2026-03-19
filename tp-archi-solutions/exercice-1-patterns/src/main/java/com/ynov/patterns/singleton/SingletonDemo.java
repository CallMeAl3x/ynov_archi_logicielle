package com.ynov.patterns.singleton;

/**
 * Demonstrates the Singleton pattern by verifying that multiple
 * calls to getInstance() always return the same object.
 */
public class SingletonDemo {

    public static void main(String[] args) {
        System.out.println("=== Singleton Pattern Demo ===\n");

        // Obtain instance three times from different "locations"
        DatabaseConnection conn1 = DatabaseConnection.getInstance();
        DatabaseConnection conn2 = DatabaseConnection.getInstance();
        DatabaseConnection conn3 = DatabaseConnection.getInstance();

        System.out.println("\n--- Identity checks ---");
        System.out.println("conn1 == conn2 : " + (conn1 == conn2));   // true
        System.out.println("conn2 == conn3 : " + (conn2 == conn3));   // true
        System.out.println("Same hashCode  : " +
            (conn1.hashCode() == conn2.hashCode() &&
             conn2.hashCode() == conn3.hashCode()));

        System.out.println("\n--- Using the single connection ---");
        conn1.connect();
        conn2.executeQuery("SELECT * FROM books WHERE available = true");
        conn3.executeQuery("INSERT INTO loans(book_id, user_id) VALUES(1, 42)");

        // Connecting again does not open a second connection
        System.out.println("\n--- Attempting double connect ---");
        conn2.connect();

        conn1.disconnect();

        System.out.println("\n=== Singleton Pattern: DONE ===");
    }
}
