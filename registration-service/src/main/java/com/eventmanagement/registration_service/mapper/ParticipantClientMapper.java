package com.eventmanagement.registration_service.mapper;

import com.eventmanagement.registration_service.client.dto.ParticipantClientResponseDto;
import com.eventmanagement.registration_service.dto.ParticipantSummary;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParticipantClientMapper {
    ParticipantSummary toSummary(ParticipantClientResponseDto participantClientResponseDto);
}
