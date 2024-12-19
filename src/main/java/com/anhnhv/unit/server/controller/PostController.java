package com.anhnhv.unit.server.controller;

import com.anhnhv.unit.server.entities.Post;
import com.anhnhv.unit.server.services.IPostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController {

    IPostService postService;

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestParam String content,
                                           @RequestParam(required = false) MultipartFile[] files) {
        return ResponseEntity.ok(postService.createPost(content, files));
    }
}
