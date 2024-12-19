package com.anhnhv.unit.server.services;

import com.anhnhv.unit.server.entities.Post;
import org.springframework.web.multipart.MultipartFile;

public interface IPostService {
    Post createPost(String content, MultipartFile[] files);
    void updatePost();
    void deletePost();
    void getPost();
    void getPosts();
}
