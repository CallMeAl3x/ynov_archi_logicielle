package com.ynov.patterns.factory;

/**
 * Common interface for all notification types.
 */
public interface Notification {

    /**
     * Sends a notification with the given message.
     *
     * @param message the content to deliver
     */
    void send(String message);

    /**
     * Returns the type label of this notification channel.
     *
     * @return notification type string (e.g. "EMAIL", "SMS", "PUSH")
     */
    String getType();
}
