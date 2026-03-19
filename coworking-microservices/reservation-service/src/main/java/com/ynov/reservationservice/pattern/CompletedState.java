package com.ynov.reservationservice.pattern;

public class CompletedState implements ReservationState {

    @Override
    public void confirm(ReservationContext context) {
        throw new IllegalStateException("Cannot confirm a completed reservation");
    }

    @Override
    public void cancel(ReservationContext context) {
        throw new IllegalStateException("Cannot cancel a completed reservation");
    }

    @Override
    public void complete(ReservationContext context) {
        throw new IllegalStateException("Reservation is already completed");
    }
}
