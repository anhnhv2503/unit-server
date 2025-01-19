package com.anhnhv.unit.server.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommentDTO {

    private Long id;
    private String content;
    private String createdAt;
    private String authorName;
    private String authorAvatar;
    private Long postId;
    private Long authorId;

}
