package com.eventmanagement.registration_service.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient(name = "participant-service", url = "http://localhost:8080")
public interface ParticipantClient {
    @GetMapping("/api/participants/{id}/exists")
    boolean participantExists(@PathVariable("id") Long id);
}
