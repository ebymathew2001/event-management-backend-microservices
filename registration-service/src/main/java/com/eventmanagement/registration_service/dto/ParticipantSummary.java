package com.eventmanagement.registration_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantSummary {
    private Long id;
    private String name;
    private String email;
}
