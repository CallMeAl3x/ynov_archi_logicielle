package com.ynov.reservationservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "room-service")
public interface RoomClient {

    @GetMapping("/api/rooms/{id}/exists")
    boolean roomExists(@PathVariable("id") Long id);

    @GetMapping("/api/rooms/{id}/available")
    boolean isRoomAvailable(@PathVariable("id") Long id);

    @PutMapping("/api/rooms/{id}/availability")
    void updateAvailability(@PathVariable("id") Long id, @RequestParam("available") boolean available);
}
