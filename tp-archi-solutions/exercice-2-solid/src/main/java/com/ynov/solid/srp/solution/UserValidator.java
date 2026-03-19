package com.ynov.solid.srp.solution;

/**
 * Single responsibility: validate User data.
 */
public class UserValidator {

    public boolean validate(User user) {
        System.out.println("[UserValidator] Validating " + user.getEmail() + "...");
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            System.out.println("[UserValidator] ERROR: Invalid email.");
            return false;
        }
        if (user.getName() == null || user.getName().isBlank()) {
            System.out.println("[UserValidator] ERROR: Name is required.");
            return false;
        }
        System.out.println("[UserValidator] Validation passed.");
        return true;
    }
}
