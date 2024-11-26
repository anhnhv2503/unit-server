package com.anhnhv.unit.server.repository;

import com.anhnhv.unit.server.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long>{
}
