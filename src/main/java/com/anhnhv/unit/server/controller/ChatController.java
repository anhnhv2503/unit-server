package com.anhnhv.unit.server.controller;

import com.anhnhv.unit.server.entities.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/{groupId}")
    public Message sendMessage(@Payload Message message,@Payload String groupId) {
        simpMessagingTemplate.convertAndSendToUser(groupId, "/queue/messages", message);
        return message;
    }
}
