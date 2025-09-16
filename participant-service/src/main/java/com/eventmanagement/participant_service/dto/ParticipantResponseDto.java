package com.eventmanagement.participant_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantResponseDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDateTime registeredAt;
}
