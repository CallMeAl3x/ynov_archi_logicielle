package com.ynov.reservationservice.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ReservationKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ReservationKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMemberSuspendEvent(String memberId) {
        kafkaTemplate.send("member-suspend", memberId);
    }

    public void sendMemberUnsuspendEvent(String memberId) {
        kafkaTemplate.send("member-unsuspend", memberId);
    }
}
