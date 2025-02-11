package com.anhnhv.unit.server.repository;

import com.anhnhv.unit.server.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long>{

    List<Message> findByConversationId(Long conversationId);
}
