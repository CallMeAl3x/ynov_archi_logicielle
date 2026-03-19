package com.ynov.solid.ocp.problem;

/**
 * PROBLEM: Violation of the Open/Closed Principle.
 *
 * Every time a new customer type is added, THIS method must be modified.
 * The class is NOT closed for modification.
 */
public class DiscountCalculator {

    /**
     * Calculates the discounted price.
     * Adding a new type requires editing this method — OCP violation.
     */
    public double calculateDiscount(String customerType, double price) {
        if ("REGULAR".equals(customerType)) {
            return price * 0.95;    // 5% discount
        } else if ("PREMIUM".equals(customerType)) {
            return price * 0.80;    // 20% discount
        } else if ("STUDENT".equals(customerType)) {
            return price * 0.85;    // 15% discount
        } else if ("EMPLOYEE".equals(customerType)) {
            return price * 0.70;    // 30% discount
        } else {
            return price;           // no discount
        }
    }
}
