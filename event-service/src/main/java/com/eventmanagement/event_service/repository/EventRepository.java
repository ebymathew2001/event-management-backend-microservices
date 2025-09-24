package com.eventmanagement.event_service.repository;

import com.eventmanagement.event_service.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {
}
