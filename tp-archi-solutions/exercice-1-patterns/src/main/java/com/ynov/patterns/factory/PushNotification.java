package com.ynov.patterns.factory;

/**
 * Push notification implementation of the Notification interface.
 */
public class PushNotification implements Notification {

    @Override
    public void send(String message) {
        System.out.println("[PUSH] Sending push notification:");
        System.out.println("       Title  : Library Alert");
        System.out.println("       Body   : " + message);
        System.out.println("       Status : Delivered to mobile device");
    }

    @Override
    public String getType() {
        return "PUSH";
    }
}
