package com.anhnhv.unit.server.controller;

import com.anhnhv.unit.server.entities.Message;
import com.anhnhv.unit.server.services.IChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final IChatService chatService;

    @MessageMapping("/{groupId}")
    public Message sendMessage(@Payload Message message,@Payload String groupId) {
        simpMessagingTemplate.convertAndSendToUser(groupId, "/queue/messages", message);
        return message;
    }

    @PostMapping("/create/new-chat/{userId}")
    public ResponseEntity<?> createChat(@PathVariable Long userId) {
        return ResponseEntity.ok(chatService.createChat(userId));
    }

}
