package com.ynov.solid.srp.problem;

/**
 * PROBLEM: Violation of the Single Responsibility Principle.
 *
 * This "god class" handles EVERYTHING:
 *   - Input validation
 *   - Database persistence
 *   - Email sending
 *   - Report generation
 *
 * Consequence: any change in any concern forces touching (and re-testing)
 * this entire class.
 */
public class UserManager {

    // --- Validation (concern 1) ---
    public boolean validateUser(String email, String name) {
        System.out.println("[UserManager] Validating user...");
        if (email == null || !email.contains("@")) {
            System.out.println("[UserManager] ERROR: Invalid email: " + email);
            return false;
        }
        if (name == null || name.isBlank()) {
            System.out.println("[UserManager] ERROR: Name is required");
            return false;
        }
        System.out.println("[UserManager] User is valid.");
        return true;
    }

    // --- Persistence (concern 2) ---
    public void saveToDatabase(String email, String name) {
        // Simulated DB insert
        System.out.println("[UserManager] INSERT INTO users (email, name) " +
                           "VALUES ('" + email + "', '" + name + "')");
        System.out.println("[UserManager] User saved to database.");
    }

    // --- Email (concern 3) ---
    public void sendWelcomeEmail(String email, String name) {
        System.out.println("[UserManager] Connecting to SMTP server...");
        System.out.println("[UserManager] Sending welcome email to " + email);
        System.out.println("[UserManager] Dear " + name + ", welcome to the library!");
    }

    // --- Reporting (concern 4) ---
    public String generateUserReport(String email, String name) {
        System.out.println("[UserManager] Generating user report...");
        return "=== User Report ===\n" +
               "Name  : " + name + "\n" +
               "Email : " + email + "\n" +
               "Status: Active\n";
    }

    // --- Orchestration (mixes all concerns in one method) ---
    public void createUser(String email, String name) {
        if (validateUser(email, name)) {
            saveToDatabase(email, name);
            sendWelcomeEmail(email, name);
            System.out.println(generateUserReport(email, name));
        }
    }
}
