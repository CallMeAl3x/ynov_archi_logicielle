package com.ynov.reservationservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "member-service")
public interface MemberClient {

    @GetMapping("/api/members/{id}/exists")
    boolean memberExists(@PathVariable("id") Long id);

    @GetMapping("/api/members/{id}/suspended")
    boolean isMemberSuspended(@PathVariable("id") Long id);

    @GetMapping("/api/members/{id}/max-bookings")
    int getMaxBookings(@PathVariable("id") Long id);
}
