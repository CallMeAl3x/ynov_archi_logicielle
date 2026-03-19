package com.ynov.solid.srp;

import com.ynov.solid.srp.problem.UserManager;
import com.ynov.solid.srp.solution.*;

/**
 * Compares the SRP violation (god class) against the SRP-compliant solution.
 */
public class SRPDemo {

    public static void main(String[] args) {
        System.out.println("=== SRP Demo ===\n");

        // --- PROBLEM: god class doing everything ---
        System.out.println("--- PROBLEM: UserManager (god class) ---");
        UserManager manager = new UserManager();
        manager.createUser("alice@example.com", "Alice");

        System.out.println();

        // --- SOLUTION: each class has one reason to change ---
        System.out.println("--- SOLUTION: specialised single-responsibility classes ---");
        UserService userService = new UserService(
                new UserValidator(),
                new UserRepository(),
                new EmailService(),
                new UserReportService()
        );
        userService.createUser("Bob", "bob@example.com");

        System.out.println("\n--- SOLUTION: invalid user (bad email) ---");
        userService.createUser("Charlie", "not-an-email");

        System.out.println("\n=== SRP Demo: DONE ===");
    }
}
