package com.ynov.solid.ocp.solution;

/**
 * SOLUTION: OCP-compliant discount calculator.
 *
 * This class is CLOSED for modification (no if/else chain).
 * It is OPEN for extension: inject any DiscountStrategy without touching this class.
 */
public class DiscountCalculator {

    private final DiscountStrategy strategy;

    public DiscountCalculator(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculate(double originalPrice) {
        double discounted = strategy.calculateDiscount(originalPrice);
        System.out.printf("[DiscountCalculator] Strategy: %-20s | Original: %.2f | After discount: %.2f%n",
                strategy.getDiscountName(), originalPrice, discounted);
        return discounted;
    }
}
