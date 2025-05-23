package com.anhnhv.unit.server.services.impl;

import com.anhnhv.unit.server.dto.request.NotificationPayload;
import com.anhnhv.unit.server.dto.response.LikeDTO;
import com.anhnhv.unit.server.entities.Like;
import com.anhnhv.unit.server.entities.Post;
import com.anhnhv.unit.server.entities.User;
import com.anhnhv.unit.server.enums.NotificationType;
import com.anhnhv.unit.server.mapper.LikeMapper;
import com.anhnhv.unit.server.repository.LikeRepository;
import com.anhnhv.unit.server.repository.PostRepository;
import com.anhnhv.unit.server.services.ILikeService;
import com.anhnhv.unit.server.services.INotificationService;
import com.anhnhv.unit.server.services.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class LikeService implements ILikeService {

    LikeRepository likeRepository;
    PostRepository postRepository;
    IUserService userService;
    LikeMapper likeMapper;
    INotificationService notificationService;



    @Override
    public LikeDTO likePost(Long postId) {
        User user = userService.getAuthenticatedUser();
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));

        Optional<Like> existingLike = likeRepository.findByUserIdAndPostId(user.getId(), postId);
        if (existingLike.isPresent()) {
            likeRepository.delete(existingLike.get());
            return new LikeDTO(false, "Like removed", null);
        }
        Like like = new Like();
        like.setUser(user);
        like.setPost(post);
        likeRepository.save(like);

        if(!user.getId().equals(post.getUser().getId())) {
            NotificationPayload payload = new NotificationPayload();
            payload.setContent(user.getFirstName() + " " + user.getLastName());
            payload.setLocation(postId + "");
            payload.setType(NotificationType.LIKE);
            payload.setRelatedId(user.getId());
            payload.setUserId(post.getUser().getId());
            payload.setPostId(postId);

            notificationService.sendNotification(payload);
        }
        return likeMapper.toLikeDTO(like);
    }
}
