package com.eventmanagement.registration_service.service;

import com.eventmanagement.registration_service.client.EventClient;
import com.eventmanagement.registration_service.client.ParticipantClient;
import com.eventmanagement.registration_service.client.dto.EventClientResponseDto;
import com.eventmanagement.registration_service.client.dto.ParticipantClientResponseDto;
import com.eventmanagement.registration_service.dto.*;
import com.eventmanagement.registration_service.entity.Registration;
import com.eventmanagement.registration_service.exception.ResourceNotFoundException;
import com.eventmanagement.registration_service.mapper.EventClientMapper;
import com.eventmanagement.registration_service.mapper.ParticipantClientMapper;
import com.eventmanagement.registration_service.mapper.RegistrationMapper;
import com.eventmanagement.registration_service.repository.RegistrationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final RegistrationMapper registrationMapper;
    private final EventClient eventClient;
    private final ParticipantClient participantClient;
    private final EventClientMapper eventClientMapper;
    private final ParticipantClientMapper participantClientMapper;

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


    public RegistrationDetailsDto getRegistrationById(Long id) {
        // Fetch registration from DB
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registration not found with id: " + id));


        EventClientResponseDto eventResponse = eventClient.getEventById(registration.getEventId());
        EventSummary eventSummary = eventClientMapper.toSummary(eventResponse);


        // 3️⃣ Call Participant Service
        ParticipantClientResponseDto participantResponse = participantClient.getParticipantById(registration.getParticipantId());
        ParticipantSummary participantSummary = participantClientMapper.toSummary(participantResponse);


        // 4️⃣ Map to RegistrationDetailsDto (MapStruct)
        return registrationMapper.toDetailsDto(registration, eventSummary, participantSummary);
    }


    public List<RegistrationDetailsDto> getAllRegistrations() {
        List<Registration> registrations = registrationRepository.findAll();

        if (registrations.isEmpty()) {
            throw new ResourceNotFoundException("No registrations found");
        }

        List<RegistrationDetailsDto> result = new ArrayList<>();

        for (Registration registration : registrations) {
            // 🔹 Get event data
            EventClientResponseDto eventResponse =
                    eventClient.getEventById(registration.getEventId());
            EventSummary eventSummary = eventClientMapper.toSummary(eventResponse);

            // 🔹 Get participant data
            ParticipantClientResponseDto participantResponse =
                    participantClient.getParticipantById(registration.getParticipantId());
            ParticipantSummary participantSummary = participantClientMapper.toSummary(participantResponse);

            // 🔹 Combine into final response
            RegistrationDetailsDto detailsDto =
                    registrationMapper.toDetailsDto(registration, eventSummary, participantSummary);

            result.add(detailsDto);
        }

        return result;
    }

    @Transactional
    public RegistrationDetailsDto updateRegistrationStatus(Long id, RegistrationStatusUpdateDto dto) {
        // 1️⃣ Fetch existing registration
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registration not found with id: " + id));

        // 2️⃣ Update status
        registration.setStatus(dto.getStatus());

        // 3️⃣ Save updated registration
        Registration updated = registrationRepository.save(registration);

        // 4️⃣ Fetch event & participant summaries again (to return full details)
        EventClientResponseDto eventResponse = eventClient.getEventById(updated.getEventId());
        EventSummary eventSummary = eventClientMapper.toSummary(eventResponse);

        ParticipantClientResponseDto participantResponse = participantClient.getParticipantById(updated.getParticipantId());
        ParticipantSummary participantSummary = participantClientMapper.toSummary(participantResponse);

        // 5️⃣ Map and return
        return registrationMapper.toDetailsDto(updated, eventSummary, participantSummary);
    }

    public void deleteRegistration(Long id) {
        if (!registrationRepository.existsById(id)) {
            throw new RuntimeException("Registration not found with id: " + id);
        }
        registrationRepository.deleteById(id);
    }


}


