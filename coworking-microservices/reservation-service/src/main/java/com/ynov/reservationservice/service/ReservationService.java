package com.ynov.reservationservice.service;

import com.ynov.reservationservice.client.MemberClient;
import com.ynov.reservationservice.client.RoomClient;
import com.ynov.reservationservice.dto.ReservationRequest;
import com.ynov.reservationservice.exception.BusinessException;
import com.ynov.reservationservice.exception.ResourceNotFoundException;
import com.ynov.reservationservice.kafka.ReservationKafkaProducer;
import com.ynov.reservationservice.model.Reservation;
import com.ynov.reservationservice.model.ReservationStatus;
import com.ynov.reservationservice.pattern.ReservationContext;
import com.ynov.reservationservice.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomClient roomClient;
    private final MemberClient memberClient;
    private final ReservationKafkaProducer kafkaProducer;

    public ReservationService(ReservationRepository reservationRepository,
                              RoomClient roomClient,
                              MemberClient memberClient,
                              ReservationKafkaProducer kafkaProducer) {
        this.reservationRepository = reservationRepository;
        this.roomClient = roomClient;
        this.memberClient = memberClient;
        this.kafkaProducer = kafkaProducer;
    }

    @Transactional(readOnly = true)
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id: " + id));
    }

    public Reservation createReservation(ReservationRequest request) {
        // Verify room exists
        if (!roomClient.roomExists(request.getRoomId())) {
            throw new BusinessException("Room not found with id: " + request.getRoomId());
        }

        // Verify room is available
        if (!roomClient.isRoomAvailable(request.getRoomId())) {
            throw new BusinessException("Room is not available with id: " + request.getRoomId());
        }

        // Verify member exists
        if (!memberClient.memberExists(request.getMemberId())) {
            throw new BusinessException("Member not found with id: " + request.getMemberId());
        }

        // Verify member is not suspended
        if (memberClient.isMemberSuspended(request.getMemberId())) {
            throw new BusinessException("Member is suspended with id: " + request.getMemberId());
        }

        // Verify member has not reached max concurrent bookings
        int maxBookings = memberClient.getMaxBookings(request.getMemberId());
        List<Reservation> activeReservations = reservationRepository
                .findByMemberIdAndStatus(request.getMemberId(), ReservationStatus.CONFIRMED);
        if (activeReservations.size() >= maxBookings) {
            throw new BusinessException("Member has reached maximum concurrent bookings (" + maxBookings + ")");
        }

        // Create reservation
        Reservation reservation = new Reservation(
                request.getRoomId(),
                request.getMemberId(),
                request.getStartDateTime(),
                request.getEndDateTime()
        );
        reservation = reservationRepository.save(reservation);

        // Mark room as unavailable
        roomClient.updateAvailability(request.getRoomId(), false);

        // Suspend member if they reached max bookings after this reservation
        if (activeReservations.size() + 1 >= maxBookings) {
            kafkaProducer.sendMemberSuspendEvent(request.getMemberId().toString());
        }

        return reservation;
    }

    public Reservation cancelReservation(Long id) {
        Reservation reservation = getReservationById(id);

        // Use State pattern
        ReservationContext context = new ReservationContext(reservation);
        context.cancel();

        reservation = reservationRepository.save(context.getReservation());

        // Mark room as available again
        roomClient.updateAvailability(reservation.getRoomId(), true);

        // Unsuspend member if they are now below max bookings
        unsuspendIfBelowQuota(reservation.getMemberId());

        return reservation;
    }

    public Reservation completeReservation(Long id) {
        Reservation reservation = getReservationById(id);

        // Use State pattern
        ReservationContext context = new ReservationContext(reservation);
        context.complete();

        reservation = reservationRepository.save(context.getReservation());

        // Mark room as available again
        roomClient.updateAvailability(reservation.getRoomId(), true);

        // Unsuspend member if they are now below max bookings
        unsuspendIfBelowQuota(reservation.getMemberId());

        return reservation;
    }

    private void unsuspendIfBelowQuota(Long memberId) {
        int maxBookings = memberClient.getMaxBookings(memberId);
        List<Reservation> activeReservations = reservationRepository
                .findByMemberIdAndStatus(memberId, ReservationStatus.CONFIRMED);
        if (activeReservations.size() < maxBookings) {
            kafkaProducer.sendMemberUnsuspendEvent(memberId.toString());
        }
    }
}
