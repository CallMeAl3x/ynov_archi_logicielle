package com.ynov.patterns.factory;

/**
 * Email implementation of the Notification interface.
 */
public class EmailNotification implements Notification {

    @Override
    public void send(String message) {
        System.out.println("[EMAIL] Sending email notification:");
        System.out.println("        Subject : Library Notification");
        System.out.println("        Body    : " + message);
        System.out.println("        Status  : Delivered via SMTP");
    }

    @Override
    public String getType() {
        return "EMAIL";
    }
}
