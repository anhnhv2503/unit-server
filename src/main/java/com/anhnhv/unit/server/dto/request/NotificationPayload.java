package com.anhnhv.unit.server.dto.request;

import com.anhnhv.unit.server.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NotificationPayload {

    private String content;
    private String location;
    private NotificationType type;
    private Long relatedId;
    private Long postId;
    private Long userId;
}
