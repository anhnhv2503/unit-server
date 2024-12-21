package com.anhnhv.unit.server.services.impl;

import com.anhnhv.unit.server.dto.response.LikeDTO;
import com.anhnhv.unit.server.entities.Like;
import com.anhnhv.unit.server.entities.Post;
import com.anhnhv.unit.server.entities.User;
import com.anhnhv.unit.server.mapper.LikeMapper;
import com.anhnhv.unit.server.repository.LikeRepository;
import com.anhnhv.unit.server.repository.PostRepository;
import com.anhnhv.unit.server.repository.UserRepository;
import com.anhnhv.unit.server.services.ILikeService;
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
    UserRepository userRepository;
    IUserService userService;
    LikeMapper likeMapper;

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

        return likeMapper.toLikeDTO(like);
    }
}
