package com.ynov.solid.dip.solution;

/**
 * SOLUTION: High-level abstraction for email sending.
 */
public interface EmailNotificationService {
    void sendEmail(String to, String subject, String body);
}
