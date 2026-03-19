package com.ynov.solid.ocp.solution;

/** 15% discount for students. */
public class StudentDiscount implements DiscountStrategy {

    @Override
    public double calculateDiscount(double price) {
        return price * 0.85;
    }

    @Override
    public String getDiscountName() {
        return "Student (15%)";
    }
}
