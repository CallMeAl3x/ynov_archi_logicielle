package com.ynov.memberservice.kafka;

import com.ynov.memberservice.service.MemberService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MemberKafkaConsumer {

    private final MemberService memberService;

    public MemberKafkaConsumer(MemberService memberService) {
        this.memberService = memberService;
    }

    @KafkaListener(topics = "member-suspend", groupId = "member-service-group")
    public void handleMemberSuspend(String memberId) {
        memberService.suspendMember(Long.parseLong(memberId));
    }

    @KafkaListener(topics = "member-unsuspend", groupId = "member-service-group")
    public void handleMemberUnsuspend(String memberId) {
        memberService.unsuspendMember(Long.parseLong(memberId));
    }
}
