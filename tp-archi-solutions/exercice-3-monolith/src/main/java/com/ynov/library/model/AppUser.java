package com.ynov.library.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "app_users")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private LocalDate membershipDate = LocalDate.now();

    // No-arg constructor
    public AppUser() {}

    // All-arg constructor
    public AppUser(Long id, String firstName, String lastName, String email, LocalDate membershipDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.membershipDate = membershipDate;
    }

    // Getters
    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public LocalDate getMembershipDate() { return membershipDate; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }
    public void setMembershipDate(LocalDate membershipDate) { this.membershipDate = membershipDate; }

    // Static Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private LocalDate membershipDate = LocalDate.now();

        public Builder id(Long id) { this.id = id; return this; }
        public Builder firstName(String firstName) { this.firstName = firstName; return this; }
        public Builder lastName(String lastName) { this.lastName = lastName; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder membershipDate(LocalDate membershipDate) { this.membershipDate = membershipDate; return this; }

        public AppUser build() {
            return new AppUser(id, firstName, lastName, email, membershipDate);
        }
    }
}
