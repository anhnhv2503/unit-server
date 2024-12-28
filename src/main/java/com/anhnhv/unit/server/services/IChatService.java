package com.anhnhv.unit.server.services;

import com.anhnhv.unit.server.dto.response.UserDTO;
import com.anhnhv.unit.server.entities.Conversation;
import com.anhnhv.unit.server.entities.Message;
import com.anhnhv.unit.server.entities.User;

import java.util.List;

public interface IChatService {

    Conversation createChat(Long userId);

    List<UserDTO> getAllUsersConversedWith();

    List<Message> getMessageByConversationId(Long recipientId);
}
