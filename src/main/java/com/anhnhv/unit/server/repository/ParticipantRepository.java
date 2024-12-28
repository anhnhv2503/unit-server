package com.anhnhv.unit.server.repository;

import com.anhnhv.unit.server.entities.Participant;
import com.anhnhv.unit.server.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long>{

    @Query("SELECT DISTINCT u FROM User u " +
            "JOIN Participant p ON p.user.id = u.id " +
            "JOIN Participant p2 ON p2.conversation.id = p.conversation.id " +
            "WHERE p2.user.id = :currentUserId AND u.id != :currentUserId")
    List<User> findAllUsersConversedWith(@Param("currentUserId") Long currentUserId);
}
