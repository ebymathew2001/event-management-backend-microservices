package com.eventmanagement.registration_service.dto;

import com.eventmanagement.registration_service.entity.RegistrationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegistrationStatusUpdateDto {
    @NotNull(message = "Status cannot be null")
    private RegistrationStatus status;
}
