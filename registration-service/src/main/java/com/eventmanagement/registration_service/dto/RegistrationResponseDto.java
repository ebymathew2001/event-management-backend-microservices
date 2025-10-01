package com.eventmanagement.registration_service.dto;

import com.eventmanagement.registration_service.entity.RegistrationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponseDto {

    private Long id;              // registration id
    private Long eventId;         // from Event Service
    private Long participantId;   // from Participant Service
    private RegistrationStatus status;
    private LocalDateTime registeredAt;
}
