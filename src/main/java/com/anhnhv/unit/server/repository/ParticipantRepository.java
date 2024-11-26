package com.anhnhv.unit.server.repository;

import com.anhnhv.unit.server.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long>{
}
