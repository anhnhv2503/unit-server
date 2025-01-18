package com.anhnhv.unit.server.services.impl;

import com.anhnhv.unit.server.entities.Notification;
import com.anhnhv.unit.server.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public Notification addNotification(String title, String content) {
        // Add a new notification to the database
        Notification notification = new Notification();
        notification.setTitle(title);
        notification.setContent(content);
        notification.setRead(false);
        return notificationRepository.save(notification);
    }

    public List<Notification> getUnreadNotifications() {
        // Get all unread notifications from the database
        simpMessagingTemplate.convertAndSend("/topic/notify", notificationRepository.findByIsReadFalse().size());
        return notificationRepository.findByIsReadFalse();
    }
}
