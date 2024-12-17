package com.anhnhv.unit.server.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ChatMessage {
    private String sender;
    private String content;
    private String timestamp;
}
