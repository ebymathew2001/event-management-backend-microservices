package com.eventmanagement.registration_service.mapper;


import com.eventmanagement.registration_service.dto.RegistrationRequestDto;
import com.eventmanagement.registration_service.dto.RegistrationResponseDto;
import com.eventmanagement.registration_service.entity.Registration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {

    Registration toEntity(RegistrationRequestDto dto);

    RegistrationResponseDto toDto(Registration registration);
}
