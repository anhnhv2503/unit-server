package com.anhnhv.unit.server.services.impl;

import com.anhnhv.unit.server.dto.response.PostDTO;
import com.anhnhv.unit.server.entities.Post;
import com.anhnhv.unit.server.entities.PostMedia;
import com.anhnhv.unit.server.entities.User;
import com.anhnhv.unit.server.enums.PostMediaType;
import com.anhnhv.unit.server.mapper.PostMapper;
import com.anhnhv.unit.server.repository.CommentRepository;
import com.anhnhv.unit.server.repository.LikeRepository;
import com.anhnhv.unit.server.repository.PostMediaRepository;
import com.anhnhv.unit.server.repository.PostRepository;
import com.anhnhv.unit.server.services.IPostService;
import com.anhnhv.unit.server.services.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PostService implements IPostService {

    PostRepository postRepository;
    IUserService userService;
    PostMediaRepository postMediaRepository;
    LikeRepository likeRepository;
    CommentRepository commentRepository;
    PostMapper postMapper;
    CloudinaryService cloudinaryService;

    @Override
    public Post createPost(String content, MultipartFile[] files) throws IOException {
        User user = userService.getAuthenticatedUser();
        Post post = new Post();
        post.setContent(content);
        post.setCreatedAt(LocalDateTime.now());
        post.setUser(user);
        List<PostMedia> listMedia = new ArrayList<>();
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                PostMedia postMedia = new PostMedia();
                postMedia.setType(PostMediaType.PHOTO);
                Map r = cloudinaryService.upload(file);
                String url = (String) r.get("url");
                postMedia.setUrl(url);
                postMedia.setPost(post);
                listMedia.add(postMedia);
            }
            post.setPostMedia(listMedia);
        }

        postRepository.save(post);
        if (!listMedia.isEmpty()) {
            postMediaRepository.saveAll(listMedia);
        }
        return post;
    }

    @Override
    public void updatePost() {

    }

    @Override
    public void deletePost() {

    }

    @Override
    public PostDTO getPost(Long id) {
        User user = userService.getAuthenticatedUser();
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        int likesCount = likeRepository.countByPostId(post.getId());
        int commentsCount = commentRepository.countByPostId(post.getId());
        boolean isLiked = likeRepository.existsByUserIdAndPostId(user.getId(), post.getId());
        return postMapper.toPostDTO(post, isLiked, likesCount, commentsCount);
    }

    @Override
    public Page<PostDTO> getPosts(Pageable pageable) {
        User user = userService.getAuthenticatedUser();
        Page<Post> posts = postRepository.findAllByOrderByCreatedAtDesc(pageable);
        return posts.map(post -> {
            int likesCount = likeRepository.countByPostId(post.getId());
            int commentsCount = commentRepository.countByPostId(post.getId());
            boolean isLiked = likeRepository.existsByUserIdAndPostId(user.getId(), post.getId());
            return postMapper.toPostDTO(post, isLiked, likesCount, commentsCount);
        });
    }

    @Override
    public Page<PostDTO> getUserPosts(Long userId, int page) {
        User user = userService.getAuthenticatedUser();
        Pageable pageable = PageRequest.of(page-1, 5);
        Page<Post> userPosts = postRepository.findAllByUserIdOrderByCreatedAtDesc(userId, pageable);
        return userPosts.map(post -> {
            int likesCount = likeRepository.countByPostId(post.getId());
            int commentsCount = commentRepository.countByPostId(post.getId());
            boolean isLiked = likeRepository.existsByUserIdAndPostId(user.getId(), post.getId());
            return postMapper.toPostDTO(post, isLiked, likesCount, commentsCount);
        });
    }
}
