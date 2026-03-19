package com.ynov.reservationservice.kafka;

import com.ynov.reservationservice.model.Reservation;
import com.ynov.reservationservice.model.ReservationStatus;
import com.ynov.reservationservice.repository.ReservationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservationKafkaConsumer {

    private final ReservationRepository reservationRepository;

    public ReservationKafkaConsumer(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @KafkaListener(topics = "room-deleted", groupId = "reservation-service-group")
    public void handleRoomDeleted(String roomId) {
        Long id = Long.parseLong(roomId);
        List<Reservation> reservations = reservationRepository.findByRoomIdAndStatus(id, ReservationStatus.CONFIRMED);
        for (Reservation reservation : reservations) {
            reservation.setStatus(ReservationStatus.CANCELLED);
            reservationRepository.save(reservation);
        }
    }

    @KafkaListener(topics = "member-deleted", groupId = "reservation-service-group")
    public void handleMemberDeleted(String memberId) {
        Long id = Long.parseLong(memberId);
        List<Reservation> reservations = reservationRepository.findByMemberId(id);
        reservationRepository.deleteAll(reservations);
    }
}
