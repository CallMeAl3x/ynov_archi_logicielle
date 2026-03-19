package com.ynov.loanservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feign client for inter-service communication with book-service.
 */
@FeignClient(name = "book-service")
public interface BookClient {

    @GetMapping("/api/books/{id}/exists")
    boolean bookExists(@PathVariable("id") Long id);

    @PutMapping("/api/books/{id}/availability")
    void updateAvailability(@PathVariable("id") Long id, @RequestParam("available") boolean available);
}
