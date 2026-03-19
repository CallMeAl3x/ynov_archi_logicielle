package com.ynov.solid.dip.solution;

/**
 * SOLUTION: DIP-compliant OrderService.
 *
 * Depends only on ABSTRACTIONS (DatabaseRepository, EmailNotificationService).
 * Concrete implementations are injected via the constructor — fully testable.
 */
public class OrderService {

    private final DatabaseRepository      database;
    private final EmailNotificationService emailService;

    /**
     * Constructor injection — depends on interfaces, not concrete classes.
     */
    public OrderService(DatabaseRepository database,
                        EmailNotificationService emailService) {
        this.database     = database;
        this.emailService = emailService;
    }

    public void processOrder(String orderId, String customerEmail) {
        System.out.println("[OrderService] Processing order: " + orderId);
        database.save("Order{id='" + orderId + "'}");
        emailService.sendEmail(
                customerEmail,
                "Order Confirmation",
                "Your order " + orderId + " has been confirmed!");
        System.out.println("[OrderService] Order " + orderId + " processed successfully.");
    }
}
