package com.ynov.patterns.factory;

/**
 * Factory that creates the appropriate Notification implementation
 * based on a type string. New notification types can be added here
 * without changing client code.
 */
public class NotificationFactory {

    /**
     * Creates and returns a Notification instance matching the given type.
     *
     * @param type  "EMAIL", "SMS", or "PUSH" (case-insensitive)
     * @return the corresponding Notification implementation
     * @throws IllegalArgumentException if the type is unknown
     */
    public static Notification createNotification(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Notification type cannot be null");
        }
        return switch (type.toUpperCase()) {
            case "EMAIL" -> new EmailNotification();
            case "SMS"   -> new SMSNotification();
            case "PUSH"  -> new PushNotification();
            default      -> throw new IllegalArgumentException(
                                "Unknown notification type: " + type);
        };
    }
}
