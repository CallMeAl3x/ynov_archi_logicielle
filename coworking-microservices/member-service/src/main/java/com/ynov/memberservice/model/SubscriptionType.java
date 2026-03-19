package com.ynov.memberservice.model;

public enum SubscriptionType {
    BASIC(2),
    PRO(5),
    ENTERPRISE(10);

    private final int maxBookings;

    SubscriptionType(int maxBookings) {
        this.maxBookings = maxBookings;
    }

    public int getMaxBookings() {
        return maxBookings;
    }
}
