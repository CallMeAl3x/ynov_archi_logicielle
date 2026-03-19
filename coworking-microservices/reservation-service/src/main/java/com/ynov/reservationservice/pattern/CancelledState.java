package com.ynov.reservationservice.pattern;

public class CancelledState implements ReservationState {

    @Override
    public void confirm(ReservationContext context) {
        throw new IllegalStateException("Cannot confirm a cancelled reservation");
    }

    @Override
    public void cancel(ReservationContext context) {
        throw new IllegalStateException("Reservation is already cancelled");
    }

    @Override
    public void complete(ReservationContext context) {
        throw new IllegalStateException("Cannot complete a cancelled reservation");
    }
}
