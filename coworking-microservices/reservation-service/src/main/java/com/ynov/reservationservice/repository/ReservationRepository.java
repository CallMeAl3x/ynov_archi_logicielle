package com.ynov.reservationservice.repository;

import com.ynov.reservationservice.model.Reservation;
import com.ynov.reservationservice.model.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByMemberIdAndStatus(Long memberId, ReservationStatus status);
    List<Reservation> findByRoomIdAndStatus(Long roomId, ReservationStatus status);
    List<Reservation> findByMemberId(Long memberId);
}
