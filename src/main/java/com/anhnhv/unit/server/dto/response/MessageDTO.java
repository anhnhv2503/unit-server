package com.anhnhv.unit.server.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class MessageDTO {
    private Long id;
    private Long conversationId;
    private Long senderId;
    private String senderName;
    private String senderAvatar;
    private String content;
    private LocalDateTime createdAt;
    private String mediaUrl;
}
