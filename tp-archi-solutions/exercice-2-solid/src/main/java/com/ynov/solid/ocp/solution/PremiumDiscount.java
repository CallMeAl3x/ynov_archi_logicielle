package com.ynov.solid.ocp.solution;

/** 20% discount for premium members. */
public class PremiumDiscount implements DiscountStrategy {

    @Override
    public double calculateDiscount(double price) {
        return price * 0.80;
    }

    @Override
    public String getDiscountName() {
        return "Premium (20%)";
    }
}
