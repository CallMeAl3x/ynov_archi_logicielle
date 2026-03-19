package com.ynov.reservationservice.pattern;

import com.ynov.reservationservice.model.ReservationStatus;

public class ConfirmedState implements ReservationState {

    @Override
    public void confirm(ReservationContext context) {
        throw new IllegalStateException("Reservation is already confirmed");
    }

    @Override
    public void cancel(ReservationContext context) {
        context.getReservation().setStatus(ReservationStatus.CANCELLED);
        context.setState(new CancelledState());
    }

    @Override
    public void complete(ReservationContext context) {
        context.getReservation().setStatus(ReservationStatus.COMPLETED);
        context.setState(new CompletedState());
    }
}
