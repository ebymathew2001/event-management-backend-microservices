package com.eventmanagement.registration_service.repository;

import com.eventmanagement.registration_service.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistrationRepository  extends JpaRepository<Registration,Long> {

    Optional<Registration> findByEventIdAndParticipantId(Long eventId, Long participantId);

}
