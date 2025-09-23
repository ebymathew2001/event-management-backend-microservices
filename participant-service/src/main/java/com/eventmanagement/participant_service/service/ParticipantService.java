package com.eventmanagement.participant_service.service;


import com.eventmanagement.participant_service.dto.ParticipantRequestDto;
import com.eventmanagement.participant_service.dto.ParticipantResponseDto;
import com.eventmanagement.participant_service.entity.Participant;
import com.eventmanagement.participant_service.exception.ResourceNotFoundException;
import com.eventmanagement.participant_service.mapper.ParticipantMapper;
import com.eventmanagement.participant_service.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final ParticipantMapper participantMapper;


    public ParticipantResponseDto createParticipant(ParticipantRequestDto dto) {

        if(participantRepository.existsByEmail(dto.getEmail())){
            throw new IllegalArgumentException("Email already registered");
        }
        if (participantRepository.existsByPhone(dto.getPhone())) {
            throw new IllegalArgumentException("Phone already registered");
        }
        Participant participant = participantMapper.toEntity(dto);
        Participant saved = participantRepository.save(participant);
        return participantMapper.toDto(saved);
    }


    public List<ParticipantResponseDto> getAllParticipants() {

        List<Participant> participants= participantRepository.findAll();
        List<ParticipantResponseDto> participantResponseDtos=new ArrayList<>();

        for(Participant participant: participants){
            ParticipantResponseDto responseDto=participantMapper.toDto(participant);
            participantResponseDtos.add(responseDto);
        }

        return participantResponseDtos;
    }


    public ParticipantResponseDto getParticipantById(Long id) {
        Participant participant=participantRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Participant with Id" + id + "not found"));

        return participantMapper.toDto(participant);

    }


    public ParticipantResponseDto updateParticipant(Long id, ParticipantRequestDto dto) {
        Participant participant = participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participant with Id" +id +"not found"));

        participant.setName(dto.getName());
        participant.setEmail(dto.getEmail());
        participant.setPhone(dto.getPhone());

        Participant updated = participantRepository.save(participant);
        return participantMapper.toDto(updated);
    }


    public void deleteParticipant(Long id) {
        if (!participantRepository.existsById(id)) {
            throw new ResourceNotFoundException("Participant with id"+id+"not found");
        }
        participantRepository.deleteById(id);
    }


}

