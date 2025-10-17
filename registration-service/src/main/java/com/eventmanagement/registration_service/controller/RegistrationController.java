package com.eventmanagement.registration_service.controller;

import com.eventmanagement.registration_service.dto.RegistrationDetailsDto;
import com.eventmanagement.registration_service.dto.RegistrationRequestDto;
import com.eventmanagement.registration_service.dto.RegistrationResponseDto;
import com.eventmanagement.registration_service.dto.RegistrationStatusUpdateDto;
import com.eventmanagement.registration_service.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<RegistrationDetailsDto> getRegistrationById(@PathVariable Long id) {
        RegistrationDetailsDto detailsDto = registrationService.getRegistrationById(id);
        return ResponseEntity.ok(detailsDto);
    }

    @GetMapping
    public ResponseEntity<List<RegistrationDetailsDto>> getAllRegistrations() {
        List<RegistrationDetailsDto> registrations = registrationService.getAllRegistrations();
        return ResponseEntity.ok(registrations);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<RegistrationDetailsDto> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody RegistrationStatusUpdateDto dto
    ) {
        RegistrationDetailsDto updated = registrationService.updateRegistrationStatus(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistration(@PathVariable Long id) {
        registrationService.deleteRegistration(id);
        return ResponseEntity.noContent().build(); // Returns 204 No Content
    }







}
