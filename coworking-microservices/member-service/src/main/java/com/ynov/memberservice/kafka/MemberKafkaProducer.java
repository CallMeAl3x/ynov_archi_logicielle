package com.ynov.memberservice.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MemberKafkaProducer {

    private static final String TOPIC = "member-deleted";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public MemberKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMemberDeletedEvent(String memberId) {
        kafkaTemplate.send(TOPIC, memberId);
    }
}
