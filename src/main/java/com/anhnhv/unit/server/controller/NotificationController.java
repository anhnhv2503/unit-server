package com.anhnhv.unit.server.controller;

import com.anhnhv.unit.server.dto.request.NotificationRequest;
import com.anhnhv.unit.server.services.impl.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notify")
@RequiredArgsConstructor
@CrossOrigin("*")
public class NotificationController {

    private final NotificationService notificationService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/create/notification")
    public ResponseEntity<?> addNotification(@RequestBody NotificationRequest request) {
        simpMessagingTemplate.convertAndSendToUser("/notification","/topic/notify", notificationService.getUnreadNotifications().size());
        return ResponseEntity.ok(notificationService.addNotification(request.getTitle(), request.getContent()));
    }

    @MessageMapping("/notify")
    public void getUnreadNotifications() {

//        return ResponseEntity.ok(notificationService.getUnreadNotifications());
    }
}
