package com.anhnhv.unit.server.services.impl;

import com.anhnhv.unit.server.dto.request.NotificationPayload;
import com.anhnhv.unit.server.dto.response.NotificationDTO;
import com.anhnhv.unit.server.entities.Notification;
import com.anhnhv.unit.server.entities.User;
import com.anhnhv.unit.server.enums.NotificationType;
import com.anhnhv.unit.server.mapper.NotificationMapper;
import com.anhnhv.unit.server.repository.NotificationRepository;
import com.anhnhv.unit.server.repository.UserRepository;
import com.anhnhv.unit.server.services.INotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService implements INotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final NotificationMapper notificationMapper;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public void sendNotification(NotificationPayload payload) {

        final String LIKE_CONTENT = " like your post ";
        final String COMMENT_CONTENT = " comment on your post ";
        final String POST_LOCATION = "/post?postId=";

        User user = userRepository.findById(payload.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Notification notification = new Notification();
        if(payload.getType().equals(NotificationType.LIKE)){
            notification.setContent(payload.getContent() + LIKE_CONTENT);
        }else if(payload.getType().equals(NotificationType.COMMENT)){
            notification.setContent(payload.getContent() + COMMENT_CONTENT);
        }
        notification.setRead(false);
        notification.setLocation(POST_LOCATION + payload.getLocation());
        notification.setType(payload.getType());
        notification.setRelatedId(payload.getRelatedId());
        notification.setPostId(payload.getPostId());
        notification.setUser(user);
        notification.setCreatedAt(LocalDateTime.now());

        notificationRepository.save(notification);
        List<Notification> unreadList = notificationRepository.findByUserIdAndIsReadFalse(user.getId());
        simpMessagingTemplate.convertAndSend("/topic/unread/"+user.getId(), unreadList.size());

    }

    @Override
    public int countUnreadNotifications() {
        User user = userService.getAuthenticatedUser();

        List<Notification> unreadList = notificationRepository.findByUserIdAndIsReadFalse(user.getId());

        return unreadList.size();
    }

    @Override
    public List<NotificationDTO> getMyNotifications() {

        User user = userService.getAuthenticatedUser();
        List<Notification> notificationList = notificationRepository.findByUserIdOrderByCreatedAtDesc(user.getId());

        return notificationList.stream().map(notificationMapper::toNotificationDTO).toList();
    }

    @Override
    public void makeRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setRead(true);
        notificationRepository.save(notification);
        List<Notification> unreadList = notificationRepository.findByUserIdAndIsReadFalse(notification.getUser().getId());
        simpMessagingTemplate.convertAndSend("/topic/unread/"+notification.getUser().getId(), unreadList.size());

    }
}
