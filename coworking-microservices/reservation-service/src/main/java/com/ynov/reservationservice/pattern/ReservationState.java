package com.ynov.reservationservice.pattern;

public interface ReservationState {
    void confirm(ReservationContext context);
    void cancel(ReservationContext context);
    void complete(ReservationContext context);
}
