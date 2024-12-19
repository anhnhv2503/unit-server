package com.anhnhv.unit.server.services.impl;

import com.anhnhv.unit.server.entities.Post;
import com.anhnhv.unit.server.entities.PostMedia;
import com.anhnhv.unit.server.entities.User;
import com.anhnhv.unit.server.repository.PostMediaRepository;
import com.anhnhv.unit.server.repository.PostRepository;
import com.anhnhv.unit.server.services.IPostService;
import com.anhnhv.unit.server.services.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PostService implements IPostService {

    PostRepository postRepository;
    IUserService userService;
    AmazonClientService amazonClientService;
    PostMediaRepository postMediaRepository;

    @Override
    public Post createPost(String content, MultipartFile[] files) {
        User user = userService.getAuthenticatedUser();
        Post post = new Post();
        post.setContent(content);
        post.setCreatedAt(LocalDateTime.now());
        post.setUser(user);
        List<PostMedia> listMedia = new ArrayList<>();

        for (MultipartFile file : files) {
            PostMedia postMedia = new PostMedia();
            postMedia.setType("media");
            postMedia.setUrl(amazonClientService.uploadFile(file));
            postMedia.setPost(post);
            listMedia.add(postMedia);
        }
        post.setPostMedia(listMedia);
        postRepository.save(post);
        for (PostMedia media : listMedia){
            postMediaRepository.save(media);
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
    public void getPost() {

    }

    @Override
    public void getPosts() {

    }
}
