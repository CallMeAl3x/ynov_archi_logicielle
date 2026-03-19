package com.ynov.solid.dip.problem;

/**
 * PROBLEM: Violation of the Dependency Inversion Principle.
 *
 * OrderService directly instantiates concrete low-level classes.
 * Changing the database or email provider requires modifying OrderService.
 * Unit-testing OrderService in isolation is impossible.
 */
public class OrderService {

    // Concrete dependencies instantiated directly — DIP violation
    private final MySQLDatabaseProblem  database     = new MySQLDatabaseProblem();
    private final EmailSenderProblem    emailSender  = new EmailSenderProblem();

    public void processOrder(String orderId, String customerEmail) {
        System.out.println("[OrderService] Processing order: " + orderId);
        database.save("INSERT INTO orders (id) VALUES ('" + orderId + "')");
        emailSender.send(customerEmail, "Order " + orderId + " confirmed!");
        System.out.println("[OrderService] Order processed.");
    }

    // ---- Inner concrete classes (low-level modules) ----

    static class MySQLDatabaseProblem {
        public void save(String sql) {
            System.out.println("[MySQLDatabase] Executing: " + sql);
        }
    }

    static class EmailSenderProblem {
        public void send(String to, String message) {
            System.out.println("[EmailSender] Sending to " + to + ": " + message);
        }
    }
}
