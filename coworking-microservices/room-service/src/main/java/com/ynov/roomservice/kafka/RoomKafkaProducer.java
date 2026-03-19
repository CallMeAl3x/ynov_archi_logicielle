package com.ynov.roomservice.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class RoomKafkaProducer {

    private static final String TOPIC = "room-deleted";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public RoomKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendRoomDeletedEvent(String roomId) {
        kafkaTemplate.send(TOPIC, roomId);
    }
}
