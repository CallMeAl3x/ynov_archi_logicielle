package com.ynov.solid.ocp.solution;

/**
 * Strategy interface for discount calculation.
 * New discount types are added by implementing this interface
 * without modifying existing code — satisfying the OCP.
 */
@FunctionalInterface
public interface DiscountStrategy {

    /**
     * Calculates and returns the final price after discount.
     *
     * @param price the original price
     * @return price after applying the discount
     */
    double calculateDiscount(double price);

    /** Returns the name of this discount type (for display purposes). */
    default String getDiscountName() {
        return this.getClass().getSimpleName();
    }
}
