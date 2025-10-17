package com.eventmanagement.registration_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventSummary {
    private Long id;
    private String title;
    private String location;
    private LocalDateTime startDate;
}
