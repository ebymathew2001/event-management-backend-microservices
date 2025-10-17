package com.eventmanagement.registration_service.mapper;


import com.eventmanagement.registration_service.dto.*;
import com.eventmanagement.registration_service.entity.Registration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {

    Registration toEntity(RegistrationRequestDto dto);

    RegistrationResponseDto toDto(Registration registration);


    @Mapping(source = "registration.id", target = "id")
    @Mapping(source = "registration.status", target = "status")
    @Mapping(source = "registration.registeredAt", target = "registeredAt")
    @Mapping(source = "eventSummary", target = "event")
    @Mapping(source = "participantSummary", target = "participant")
    RegistrationDetailsDto toDetailsDto(
            Registration registration,
            EventSummary eventSummary,
            ParticipantSummary participantSummary
    );


}
