package com.ynov.solid.srp.solution;

/**
 * Single responsibility: send emails.
 */
public class EmailService {

    public void sendWelcomeEmail(User user) {
        System.out.println("[EmailService] Connecting to SMTP...");
        System.out.println("[EmailService] To      : " + user.getEmail());
        System.out.println("[EmailService] Subject : Welcome to the Library!");
        System.out.println("[EmailService] Body    : Dear " + user.getName() +
                           ", your account is ready.");
        System.out.println("[EmailService] Email sent successfully.");
    }
}
