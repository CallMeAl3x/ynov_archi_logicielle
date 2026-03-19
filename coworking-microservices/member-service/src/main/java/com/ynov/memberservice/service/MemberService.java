package com.ynov.memberservice.service;

import com.ynov.memberservice.exception.ResourceNotFoundException;
import com.ynov.memberservice.kafka.MemberKafkaProducer;
import com.ynov.memberservice.model.Member;
import com.ynov.memberservice.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberKafkaProducer memberKafkaProducer;

    public MemberService(MemberRepository memberRepository, MemberKafkaProducer memberKafkaProducer) {
        this.memberRepository = memberRepository;
        this.memberKafkaProducer = memberKafkaProducer;
    }

    @Transactional(readOnly = true)
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + id));
    }

    public Member createMember(Member member) {
        member.setMaxConcurrentBookings(member.getSubscriptionType().getMaxBookings());
        member.setSuspended(false);
        return memberRepository.save(member);
    }

    public Member updateMember(Long id, Member memberDetails) {
        Member member = getMemberById(id);
        member.setFullName(memberDetails.getFullName());
        member.setEmail(memberDetails.getEmail());
        member.setSubscriptionType(memberDetails.getSubscriptionType());
        member.setMaxConcurrentBookings(memberDetails.getSubscriptionType().getMaxBookings());
        member.setSuspended(memberDetails.isSuspended());
        return memberRepository.save(member);
    }

    public void deleteMember(Long id) {
        Member member = getMemberById(id);
        memberRepository.delete(member);
        memberKafkaProducer.sendMemberDeletedEvent(id.toString());
    }

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return memberRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public boolean isSuspended(Long id) {
        Member member = getMemberById(id);
        return member.isSuspended();
    }

    public void suspendMember(Long memberId) {
        Member member = getMemberById(memberId);
        member.setSuspended(true);
        memberRepository.save(member);
    }

    public void unsuspendMember(Long memberId) {
        Member member = getMemberById(memberId);
        member.setSuspended(false);
        memberRepository.save(member);
    }
}
