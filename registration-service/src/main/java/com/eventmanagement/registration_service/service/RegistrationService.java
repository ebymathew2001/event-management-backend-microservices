package com.eventmanagement.registration_service.service;

import com.eventmanagement.registration_service.client.EventClient;
import com.eventmanagement.registration_service.client.ParticipantClient;
import com.eventmanagement.registration_service.dto.RegistrationRequestDto;
import com.eventmanagement.registration_service.dto.RegistrationResponseDto;
import com.eventmanagement.registration_service.entity.Registration;
import com.eventmanagement.registration_service.exception.ResourceNotFoundException;
import com.eventmanagement.registration_service.mapper.RegistrationMapper;
import com.eventmanagement.registration_service.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final RegistrationMapper registrationMapper;
    private final EventClient eventClient;
    private final ParticipantClient participantClient;

    public RegistrationResponseDto createRegistration(RegistrationRequestDto requestDto) {

        if (!eventClient.eventExists(requestDto.getEventId())) {
            throw new ResourceNotFoundException("Event not found with id: " + requestDto.getEventId());
        }

        if (!participantClient.participantExists(requestDto.getParticipantId())) {
            throw new ResourceNotFoundException("Participant not found with id: " + requestDto.getParticipantId());
        }

        Optional<Registration> existing= registrationRepository.
                findByEventIdAndParticipantId(requestDto.getEventId(),requestDto.getParticipantId());


        if (existing.isPresent()) {
            throw new IllegalArgumentException("Participant already registered for this event.");
        }

        Registration registration = registrationMapper.toEntity(requestDto);
        Registration saved = registrationRepository.save(registration);

        return registrationMapper.toDto(saved);
    }
}
