package com.anhnhv.unit.server.dto.response;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PostDTO {

    private Long id;
    private String author;
    private Long authorId;
    private String avatar;
    private String content;
    private List<String> media;
    private int likesCount;
    private int commentsCount;
    private String createdAt;
    private boolean liked;

}
