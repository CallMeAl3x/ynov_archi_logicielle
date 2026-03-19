package com.ynov.solid.srp.solution;

/**
 * Plain data object representing a library user.
 * Single responsibility: hold user data.
 */
public class User {

    private final String name;
    private final String email;

    public User(String name, String email) {
        this.name  = name;
        this.email = email;
    }

    public String getName()  { return name;  }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return "User{name='" + name + "', email='" + email + "'}";
    }
}
