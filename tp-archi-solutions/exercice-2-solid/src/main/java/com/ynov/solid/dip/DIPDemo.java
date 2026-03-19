package com.ynov.solid.dip;

import com.ynov.solid.dip.problem.OrderService;
import com.ynov.solid.dip.solution.*;

/**
 * Demonstrates DIP: direct dependency problem vs interface-based solution.
 */
public class DIPDemo {

    public static void main(String[] args) {
        System.out.println("=== DIP Demo ===\n");

        // --- PROBLEM ---
        System.out.println("--- PROBLEM: OrderService creates its own concrete dependencies ---");
        OrderService problematic = new OrderService();
        problematic.processOrder("ORD-001", "alice@example.com");

        System.out.println();

        // --- SOLUTION with MySQL + SMTP ---
        System.out.println("--- SOLUTION: inject MySQL + SMTP ---");
        com.ynov.solid.dip.solution.OrderService mysqlService =
                new com.ynov.solid.dip.solution.OrderService(
                        new MySQLDatabase(),
                        new SMTPEmailSender()
                );
        mysqlService.processOrder("ORD-002", "bob@example.com");

        System.out.println();

        // --- SOLUTION swapped to PostgreSQL (zero change in OrderService) ---
        System.out.println("--- SOLUTION: swap to PostgreSQL (OrderService unchanged) ---");
        com.ynov.solid.dip.solution.OrderService pgService =
                new com.ynov.solid.dip.solution.OrderService(
                        new PostgreSQLDatabase(),
                        new SMTPEmailSender()
                );
        pgService.processOrder("ORD-003", "charlie@example.com");

        System.out.println();

        // --- SOLUTION with a mock (test double) — no real I/O ---
        System.out.println("--- SOLUTION: in-memory mock for testing ---");
        DatabaseRepository mockDb = data ->
                System.out.println("[MockDB] Stored: " + data);
        EmailNotificationService mockEmail = (to, subject, body) ->
                System.out.println("[MockEmail] Email to " + to + " captured.");

        com.ynov.solid.dip.solution.OrderService testService =
                new com.ynov.solid.dip.solution.OrderService(mockDb, mockEmail);
        testService.processOrder("ORD-TEST-001", "test@example.com");

        System.out.println("\n=== DIP Demo: DONE ===");
    }
}
