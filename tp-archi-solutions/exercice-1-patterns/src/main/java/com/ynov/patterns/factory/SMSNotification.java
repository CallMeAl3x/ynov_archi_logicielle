package com.ynov.patterns.factory;

/**
 * SMS implementation of the Notification interface.
 */
public class SMSNotification implements Notification {

    @Override
    public void send(String message) {
        System.out.println("[SMS] Sending SMS notification:");
        System.out.println("      To     : +33 6 XX XX XX XX");
        System.out.println("      Body   : " + message);
        System.out.println("      Status : Sent via SMS gateway");
    }

    @Override
    public String getType() {
        return "SMS";
    }
}
