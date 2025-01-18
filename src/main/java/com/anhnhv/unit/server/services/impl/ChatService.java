package com.anhnhv.unit.server.services.impl;

import com.anhnhv.unit.server.dto.request.MessagePayload;
import com.anhnhv.unit.server.dto.response.MessageDTO;
import com.anhnhv.unit.server.dto.response.UserDTO;
import com.anhnhv.unit.server.entities.Conversation;
import com.anhnhv.unit.server.entities.Message;
import com.anhnhv.unit.server.entities.Participant;
import com.anhnhv.unit.server.entities.User;
import com.anhnhv.unit.server.mapper.ChatMapper;
import com.anhnhv.unit.server.mapper.UserMapper;
import com.anhnhv.unit.server.repository.ConversationRepository;
import com.anhnhv.unit.server.repository.MessageRepository;
import com.anhnhv.unit.server.repository.ParticipantRepository;
import com.anhnhv.unit.server.repository.UserRepository;
import com.anhnhv.unit.server.services.IChatService;
import com.anhnhv.unit.server.services.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService implements IChatService {

    private final UserRepository userRepository;
    private final ParticipantRepository participantRepository;
    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final IUserService userService;
    private final UserMapper userMapper;
    private final ChatMapper chatMapper;

    @Override
    public List<UserDTO> getAllUsersConversedWith() {
        User user = userService.getAuthenticatedUser();
        List<User> currentChatUsers = participantRepository.findAllUsersConversedWith(user.getId());

        return currentChatUsers.stream()
                .map(userMapper::mapToUserDTO)
                .toList();
    }

    @Override
    public List<MessageDTO> getMessageByConversationId(Long recipientId) {

        User requestUser = userService.getAuthenticatedUser();
        Conversation conversation = conversationRepository.findConversationByUserIds(requestUser.getId(), recipientId)
                .orElseThrow(() -> new RuntimeException("Conversation not found"));

        List<Message> messageList = messageRepository.findByConversationId(conversation.getId());

        return messageList.stream()
                .map(message ->
                        chatMapper.toMessageDTO(message, requestUser.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public MessageDTO sendMessage(MessagePayload message) {
        User sender = userRepository.findById(message.getSenderId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Conversation conversation = conversationRepository.findConversationByUserIds(message.getSenderId(), message.getRecipientId())
                .orElseThrow(() -> new RuntimeException("Conversation not found"));

        Message newMessage = new Message();
        newMessage.setContent(message.getContent());
        newMessage.setSender(sender);
        newMessage.setConversation(conversation);
        newMessage.setCreatedAt(LocalDateTime.now());

        messageRepository.save(newMessage);
        return chatMapper.toMessageDTO(newMessage, sender.getId());
    }

    @Override
    public Conversation createChat(Long userId) {
        User requestUser = userService.getAuthenticatedUser();
        User requestedUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Conversation isExistedConversation = conversationRepository
                .findConversationByUserIds(requestedUser.getId(), requestUser.getId())
                .orElseThrow(() -> new RuntimeException("Conversation not found"));

        if(isExistedConversation != null) {
            log.info("Conversation existed");
            return isExistedConversation;
        }

        Conversation conversation = new Conversation();
        conversation.setTitle(requestUser.getUsername() + " - " + requestedUser.getUsername());
        conversation.setCreatedAt(Date.valueOf(LocalDate.now()));
        conversationRepository.save(conversation);
        Participant participant = new Participant();
        participant.setConversation(conversation);
        participant.setUser(requestUser);
        participantRepository.save(participant);
        participant = new Participant();
        participant.setConversation(conversation);
        participant.setUser(requestedUser);
        participantRepository.save(participant);

        return conversation;
    }
}
