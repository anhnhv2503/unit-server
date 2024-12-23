package com.anhnhv.unit.server.services.impl;

import com.anhnhv.unit.server.repository.ConversationRepository;
import com.anhnhv.unit.server.repository.MessageRepository;
import com.anhnhv.unit.server.repository.ParticipantRepository;
import com.anhnhv.unit.server.repository.UserRepository;
import com.anhnhv.unit.server.services.IChatService;
import com.anhnhv.unit.server.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService implements IChatService {

    private final UserRepository userRepository;
    private final ParticipantRepository participantRepository;
    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final IUserService userService;

    @Override
    public Object createChat() {
        return null;
    }
}
