package com.eventmanagement.participant_service.controller;

import com.eventmanagement.participant_service.dto.ParticipantRequestDto;
import com.eventmanagement.participant_service.dto.ParticipantResponseDto;
import com.eventmanagement.participant_service.service.ParticipantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participants")
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantService participantService;

    @PostMapping
    public ResponseEntity<ParticipantResponseDto> createParticipant(
            @Valid @RequestBody ParticipantRequestDto dto
    ) {
        ParticipantResponseDto participantResponseDto= participantService.createParticipant(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(participantResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<ParticipantResponseDto>> getAllParticipants() {
        return ResponseEntity.ok(participantService.getAllParticipants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipantResponseDto> getParticipantById(@PathVariable Long id) {
        return ResponseEntity.ok(participantService.getParticipantById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipantResponseDto> updateParticipant(
            @PathVariable Long id,
            @Valid @RequestBody ParticipantRequestDto dto
    ) {
        return ResponseEntity.ok(participantService.updateParticipant(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable Long id) {
        participantService.deleteParticipant(id);
        return ResponseEntity.noContent().build();
    }


}
