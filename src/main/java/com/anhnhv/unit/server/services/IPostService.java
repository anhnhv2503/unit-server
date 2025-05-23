package com.anhnhv.unit.server.services;

import com.anhnhv.unit.server.dto.response.PostDTO;
import com.anhnhv.unit.server.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IPostService {
    Post createPost(String content, MultipartFile[] files) throws IOException;
    void updatePost();
    void deletePost();
    PostDTO getPost(Long id);
    Page<PostDTO> getPosts(Pageable pageable);
    Page<PostDTO> getUserPosts(Long userId, int page);
}
