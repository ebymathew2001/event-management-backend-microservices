package com.eventmanagement.registration_service.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "event-service", url = "http://localhost:8081")
public interface EventClient {

    @GetMapping("/api/events/{id}/exists")
    boolean eventExists(@PathVariable("id") Long id);
}