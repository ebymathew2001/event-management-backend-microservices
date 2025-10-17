package com.eventmanagement.registration_service.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantClientResponseDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDateTime registeredAt;
}
