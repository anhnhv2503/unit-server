package com.anhnhv.unit.server.controller;

import com.anhnhv.unit.server.services.INotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final INotificationService notificationService;

    @GetMapping("/unread")
    public ResponseEntity<?> unread() {
        return ResponseEntity.ok(notificationService.countUnreadNotifications());
    }

    @GetMapping("/my-notifications")
    public ResponseEntity<?> myNotifications() {
        return ResponseEntity.ok(notificationService.getMyNotifications());
    }

    @PutMapping("/seen/{notificationId}")
    public ResponseEntity<?> seen(@PathVariable Long notificationId) {
        notificationService.makeRead(notificationId);
        return ResponseEntity.ok().build();
    }
}
