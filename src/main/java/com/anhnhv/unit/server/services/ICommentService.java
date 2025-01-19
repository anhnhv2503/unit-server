package com.anhnhv.unit.server.services;

import com.anhnhv.unit.server.dto.response.CommentDTO;
import org.springframework.data.domain.Page;

public interface ICommentService {

    CommentDTO createComment(Long postId, String content);

    Page<CommentDTO> getAllCommentsByPostId(Long postId, int pageable);
}
