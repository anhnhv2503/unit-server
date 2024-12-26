package com.anhnhv.unit.server.repository;

import com.anhnhv.unit.server.entities.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    @Query("SELECT c FROM Conversation c " +
            "JOIN Participant p1 ON p1.conversation = c " +
            "JOIN Participant p2 ON p2.conversation = c " +
            "WHERE p1.user.id = :userId1 AND p2.user.id = :userId2 ")
    Optional<Conversation> findConversationByUserIds(@Param("userId1") Long userId1,
                                                     @Param("userId2") Long userId2);
}
