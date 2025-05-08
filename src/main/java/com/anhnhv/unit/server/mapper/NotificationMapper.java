package com.anhnhv.unit.server.mapper;

import com.anhnhv.unit.server.dto.response.NotificationDTO;
import com.anhnhv.unit.server.entities.Notification;
import com.anhnhv.unit.server.entities.Post;
import com.anhnhv.unit.server.entities.User;
import com.anhnhv.unit.server.repository.PostRepository;
import com.anhnhv.unit.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationMapper {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public NotificationDTO toNotificationDTO(Notification notification) {
        User interactUser = userRepository.findById(notification.getRelatedId()).orElseThrow(() -> new RuntimeException("User not found"));
        Post post = postRepository.findById(notification.getPostId()).orElseThrow(() -> new RuntimeException("Post not found"));
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setId(notification.getId());
        notificationDTO.setContent(notification.getContent());
        notificationDTO.setRead(notification.isRead());
        notificationDTO.setLocation(notification.getLocation());
        notificationDTO.setType(notification.getType());
        notificationDTO.setInteractorId(interactUser.getId());
        notificationDTO.setInteractorName(interactUser.getFirstName() + " " + interactUser.getLastName());
        notificationDTO.setInteractorAvatar(interactUser.getAvatar());
        notificationDTO.setPostId(notification.getPostId());
        notificationDTO.setCreatedAt(notification.getCreatedAt());
        notificationDTO.setPostTitle(post.getContent().substring(0, post.getContent().lastIndexOf(" ")));
        return notificationDTO;
    }
}
