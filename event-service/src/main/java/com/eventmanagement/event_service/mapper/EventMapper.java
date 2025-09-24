package com.eventmanagement.event_service.mapper;

import com.eventmanagement.event_service.dto.EventRequestDto;
import com.eventmanagement.event_service.dto.EventResponseDto;
import com.eventmanagement.event_service.entity.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {

    Event toEntity(EventRequestDto eventRequestDto);

    EventResponseDto toDto(Event event);
}
