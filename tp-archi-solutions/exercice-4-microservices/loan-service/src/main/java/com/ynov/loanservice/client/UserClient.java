package com.ynov.loanservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign client for inter-service communication with user-service.
 */
@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/api/users/{id}/exists")
    boolean userExists(@PathVariable("id") Long id);
}
