package com.eventmanagement.participant_service.mapper;


import com.eventmanagement.participant_service.dto.ParticipantRequestDto;
import com.eventmanagement.participant_service.dto.ParticipantResponseDto;
import com.eventmanagement.participant_service.entity.Participant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParticipantMapper {

    Participant toEntity(ParticipantRequestDto participantRequestDto);

    ParticipantResponseDto toDto(Participant participant);

}
