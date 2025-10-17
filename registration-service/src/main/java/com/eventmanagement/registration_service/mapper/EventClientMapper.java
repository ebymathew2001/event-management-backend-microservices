package com.eventmanagement.registration_service.mapper;


import com.eventmanagement.registration_service.client.dto.EventClientResponseDto;
import com.eventmanagement.registration_service.dto.EventSummary;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventClientMapper {
    EventSummary toSummary(EventClientResponseDto eventClientResponseDto);
}
