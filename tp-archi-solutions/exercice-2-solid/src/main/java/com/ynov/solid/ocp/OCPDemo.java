package com.ynov.solid.ocp;

import com.ynov.solid.ocp.problem.DiscountCalculator;
import com.ynov.solid.ocp.solution.*;

/**
 * Demonstrates OCP: problem (if/else chain) vs solution (strategy pattern).
 */
public class OCPDemo {

    public static void main(String[] args) {
        System.out.println("=== OCP Demo ===\n");

        double basePrice = 100.0;

        // --- PROBLEM: adding a new type requires editing the method ---
        System.out.println("--- PROBLEM: if/else chain ---");
        DiscountCalculator problemCalc = new DiscountCalculator();
        System.out.println("REGULAR  -> " + problemCalc.calculateDiscount("REGULAR",  basePrice));
        System.out.println("PREMIUM  -> " + problemCalc.calculateDiscount("PREMIUM",  basePrice));
        System.out.println("STUDENT  -> " + problemCalc.calculateDiscount("STUDENT",  basePrice));
        System.out.println("EMPLOYEE -> " + problemCalc.calculateDiscount("EMPLOYEE", basePrice));

        System.out.println();

        // --- SOLUTION: inject any strategy without modifying DiscountCalculator ---
        System.out.println("--- SOLUTION: Strategy pattern ---");
        new com.ynov.solid.ocp.solution.DiscountCalculator(new RegularDiscount()).calculate(basePrice);
        new com.ynov.solid.ocp.solution.DiscountCalculator(new PremiumDiscount()).calculate(basePrice);
        new com.ynov.solid.ocp.solution.DiscountCalculator(new StudentDiscount()).calculate(basePrice);

        // New type added WITHOUT modifying DiscountCalculator:
        System.out.println("--- Adding VIP discount (no change to DiscountCalculator) ---");
        DiscountStrategy vipDiscount = price -> {
            System.out.print("[VIP] ");
            return price * 0.50;   // 50% discount — anonymous class / lambda
        };
        double result = new com.ynov.solid.ocp.solution.DiscountCalculator(vipDiscount).calculate(basePrice);
        System.out.println("VIP price: " + result);

        System.out.println("\n=== OCP Demo: DONE ===");
    }
}
