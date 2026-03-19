package com.ynov.solid.ocp.solution;

/** 5% discount for regular customers. */
public class RegularDiscount implements DiscountStrategy {

    @Override
    public double calculateDiscount(double price) {
        return price * 0.95;
    }

    @Override
    public String getDiscountName() {
        return "Regular (5%)";
    }
}
