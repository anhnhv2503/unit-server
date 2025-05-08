package com.anhnhv.unit.server.dto.response;

import com.anhnhv.unit.server.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotificationDTO {

    private Long id;
    private String content;
    private boolean isRead;
    private String location;
    private NotificationType type;
    private Long interactorId;
    private String interactorName;
    private String interactorAvatar;
    private Long postId;
    private LocalDateTime createdAt;
    private String postTitle;

}
