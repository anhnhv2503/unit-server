package com.anhnhv.unit.server.services;

import com.anhnhv.unit.server.entities.Conversation;

public interface IChatService {

    Conversation createChat(Long userId);
}
