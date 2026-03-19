package com.ynov.patterns.factory;

/**
 * Demonstrates the Factory pattern with all three notification types.
 */
public class FactoryDemo {

    public static void main(String[] args) {
        System.out.println("=== Factory Pattern Demo ===\n");

        String[] types = {"EMAIL", "SMS", "PUSH"};

        for (String type : types) {
            Notification notification = NotificationFactory.createNotification(type);
            System.out.println("Created notification of type: " + notification.getType());
            notification.send("Your book 'Clean Code' is ready for pickup.");
            System.out.println();
        }

        // Demonstrate that client code only depends on the interface
        System.out.println("--- Polymorphic usage via interface ---");
        Notification n = NotificationFactory.createNotification("email");  // case-insensitive
        System.out.println("Type: " + n.getType());
        n.send("Your loan expires in 3 days.");

        System.out.println("\n=== Factory Pattern: DONE ===");
    }
}
