package com.anhnhv.unit.server.services;

import com.anhnhv.unit.server.entities.User;

public interface INotificationService {

    void sendNotification(String title, User user);
}
