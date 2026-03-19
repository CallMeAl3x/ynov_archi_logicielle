package com.ynov.solid.srp.solution;

/**
 * Single responsibility: persist User data.
 */
public class UserRepository {

    public void save(User user) {
        System.out.println("[UserRepository] INSERT INTO users (email, name) " +
                           "VALUES ('" + user.getEmail() + "', '" + user.getName() + "')");
        System.out.println("[UserRepository] User persisted successfully.");
    }

    public User findByEmail(String email) {
        // Simulated lookup
        System.out.println("[UserRepository] SELECT * FROM users WHERE email = '" + email + "'");
        return new User("Alice Example", email);
    }
}
