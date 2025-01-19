package com.anhnhv.unit.server.mapper;

import com.anhnhv.unit.server.dto.response.CommentDTO;
import com.anhnhv.unit.server.entities.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentDTO toCommentDTO(Comment comment){
        return CommentDTO.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .authorName(comment.getUser().getFirstName() + " " + comment.getUser().getLastName())
                .authorAvatar(comment.getUser().getAvatar())
                .postId(comment.getPost().getId())
                .authorId(comment.getUser().getId())
                .build();
    }


}
