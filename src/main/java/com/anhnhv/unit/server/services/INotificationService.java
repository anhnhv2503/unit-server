package com.anhnhv.unit.server.services;

import com.anhnhv.unit.server.dto.request.NotificationPayload;
import com.anhnhv.unit.server.entities.Notification;

import java.util.List;

public interface INotificationService {

    void sendNotification(NotificationPayload payload);

    int countUnreadNotifications();

    List<Notification> getMyNotifications();
}
