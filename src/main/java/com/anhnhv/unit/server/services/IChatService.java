package com.anhnhv.unit.server.services;

import com.anhnhv.unit.server.dto.request.MessagePayload;
import com.anhnhv.unit.server.dto.response.MessageDTO;
import com.anhnhv.unit.server.dto.response.UserDTO;
import com.anhnhv.unit.server.entities.Conversation;
import com.anhnhv.unit.server.entities.Message;
import com.anhnhv.unit.server.entities.User;

import java.util.List;

public interface IChatService {

    Conversation createChat(Long userId);

    List<UserDTO> getAllUsersConversedWith();

    List<MessageDTO> getMessageByConversationId(Long recipientId);

    MessageDTO sendMessage(MessagePayload message);
}
