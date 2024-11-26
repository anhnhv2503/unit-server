package com.anhnhv.unit.server.repository;

import com.anhnhv.unit.server.entities.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
}
