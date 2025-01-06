package com.anhnhv.unit.server.controller;

import com.anhnhv.unit.server.dto.request.MessagePayload;
import com.anhnhv.unit.server.entities.Message;
import com.anhnhv.unit.server.services.IChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/current-user/conversations")
    public ResponseEntity<?> getAllUsersConversedWith() {
        return ResponseEntity.ok(chatService.getAllUsersConversedWith());
    }

    @GetMapping("/conversation/{recipientId}")
    public ResponseEntity<?> getMessageByConversationId(@PathVariable Long recipientId) {
        return ResponseEntity.ok(chatService.getMessageByConversationId(recipientId));
    }

    @PostMapping("/send/message/{recipientId}")
    public ResponseEntity<?> sendMessage(@PathVariable Long recipientId, @RequestBody MessagePayload message) {
        return ResponseEntity.ok(chatService.sendMessage(recipientId, message));
    }

}
