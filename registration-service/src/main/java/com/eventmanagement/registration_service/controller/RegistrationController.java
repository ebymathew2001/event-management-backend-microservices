package com.eventmanagement.registration_service.controller;

import com.eventmanagement.registration_service.dto.RegistrationRequestDto;
import com.eventmanagement.registration_service.dto.RegistrationResponseDto;
import com.eventmanagement.registration_service.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registrations")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<RegistrationResponseDto> createRegistration(
            @RequestBody RegistrationRequestDto requestDto) {
        RegistrationResponseDto response = registrationService.createRegistration(requestDto);
        return ResponseEntity.ok(response);
    }
}
