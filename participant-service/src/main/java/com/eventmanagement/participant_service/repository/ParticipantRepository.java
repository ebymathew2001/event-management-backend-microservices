package com.eventmanagement.participant_service.repository;

import com.eventmanagement.participant_service.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant,Long> {

    boolean existsByEmail(String email);



}
