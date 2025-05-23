package com.anhnhv.unit.server.services.impl;

import com.anhnhv.unit.server.dto.request.NotificationPayload;
import com.anhnhv.unit.server.dto.response.CommentDTO;
import com.anhnhv.unit.server.entities.Comment;
import com.anhnhv.unit.server.entities.Post;
import com.anhnhv.unit.server.entities.User;
import com.anhnhv.unit.server.enums.NotificationType;
import com.anhnhv.unit.server.mapper.CommentMapper;
import com.anhnhv.unit.server.repository.CommentRepository;
import com.anhnhv.unit.server.repository.PostRepository;
import com.anhnhv.unit.server.services.ICommentService;
import com.anhnhv.unit.server.services.INotificationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentService implements ICommentService {

    CommentRepository commentRepository;
    PostRepository postRepository;
    UserService userService;
    CommentMapper commentMapper;
    INotificationService notificationService;

    @Override
    public CommentDTO createComment(Long postId, String content) {
        User user = userService.getAuthenticatedUser();
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setContent(content);
        comment.setCreatedAt(LocalDateTime.now().toString());
       Comment savedComment = commentRepository.save(comment);

        if(!user.getId().equals(post.getUser().getId())) {
            NotificationPayload payload = new NotificationPayload();
            payload.setContent(user.getFirstName() + " " + user.getLastName());
            payload.setLocation(postId + "");
            payload.setType(NotificationType.COMMENT);
            payload.setRelatedId(user.getId());
            payload.setUserId(post.getUser().getId());
            payload.setPostId(postId);

            notificationService.sendNotification(payload);
        }

       return commentMapper.toCommentDTO(savedComment);
    }

    @Override
    public Page<CommentDTO> getAllCommentsByPostId(Long postId, int page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<Comment> comments = commentRepository.findAllByPostIdOrderByCreatedAtDesc(postId, pageable);
        return comments.map(commentMapper::toCommentDTO);
    }
}
