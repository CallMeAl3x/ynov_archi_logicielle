package com.ynov.memberservice.controller;

import com.ynov.memberservice.model.Member;
import com.ynov.memberservice.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.createMember(member));
    }

    @PutMapping("/{id}")
    public Member updateMember(@PathVariable Long id, @RequestBody Member member) {
        return memberService.updateMember(id, member);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/exists")
    public boolean memberExists(@PathVariable Long id) {
        return memberService.existsById(id);
    }

    @GetMapping("/{id}/suspended")
    public boolean isMemberSuspended(@PathVariable Long id) {
        return memberService.isSuspended(id);
    }

    @GetMapping("/{id}/max-bookings")
    public int getMaxBookings(@PathVariable Long id) {
        return memberService.getMemberById(id).getMaxConcurrentBookings();
    }
}
