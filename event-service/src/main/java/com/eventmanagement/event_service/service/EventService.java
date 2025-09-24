package com.eventmanagement.event_service.service;

import com.eventmanagement.event_service.dto.EventRequestDto;
import com.eventmanagement.event_service.dto.EventResponseDto;
import com.eventmanagement.event_service.entity.Event;
import com.eventmanagement.event_service.exception.ResourceNotFoundException;
import com.eventmanagement.event_service.mapper.EventMapper;
import com.eventmanagement.event_service.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public EventResponseDto createEvent(EventRequestDto dto) {
        Event event = eventMapper.toEntity(dto);
        Event saved = eventRepository.save(event);
        return eventMapper.toDto(saved);
    }

    public List<EventResponseDto> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        List<EventResponseDto> responseDtos = new ArrayList<>();

        for (Event event : events) {
            EventResponseDto dto = eventMapper.toDto(event);
            responseDtos.add(dto);
        }

        return responseDtos;
    }


    public EventResponseDto getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event with Id " + id + " not found"));
        return eventMapper.toDto(event);
    }

    public EventResponseDto updateEvent(Long id, EventRequestDto dto) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event with Id " + id + " not found"));

        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setLocation(dto.getLocation());
        event.setStartDate(dto.getStartDate());

        Event updated = eventRepository.save(event);
        return eventMapper.toDto(updated);
    }

    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new ResourceNotFoundException("Event with Id " + id + " not found");
        }
        eventRepository.deleteById(id);
    }
}
