package com.ynov.solid.dip.solution;

/**
 * Low-level module: SMTP implementation of EmailNotificationService.
 */
public class SMTPEmailSender implements EmailNotificationService {

    @Override
    public void sendEmail(String to, String subject, String body) {
        System.out.println("[SMTPEmailSender] Connecting to SMTP server...");
        System.out.println("[SMTPEmailSender] To     : " + to);
        System.out.println("[SMTPEmailSender] Subject: " + subject);
        System.out.println("[SMTPEmailSender] Body   : " + body);
        System.out.println("[SMTPEmailSender] Email dispatched.");
    }
}
