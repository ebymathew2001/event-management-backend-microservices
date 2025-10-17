package com.eventmanagement.registration_service.dto;

import com.eventmanagement.registration_service.entity.RegistrationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDetailsDto {
    private Long id;
    private RegistrationStatus status;
    private LocalDateTime registeredAt;
    private EventSummary event;                  // simplified event info
    private ParticipantSummary participant;      // simplified participant info
}
