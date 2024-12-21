package com.anhnhv.unit.server.controller;

import com.anhnhv.unit.server.dto.response.PostDTO;
import com.anhnhv.unit.server.entities.Post;
import com.anhnhv.unit.server.services.IPostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        return new ResponseEntity<>(postService.createPost(content, files), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getPosts(@RequestParam(name = "page", defaultValue = "1") int page,
                                      @RequestParam(name = "size", defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return ResponseEntity.ok(postService.getPosts(pageable));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<PostDTO> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPost(id));
    }

    @GetMapping("/user/post/{userId}/{page}")
    public ResponseEntity<?> getPostsByUser(@PathVariable Long userId, @PathVariable int page){
        return ResponseEntity.ok(postService.getUserPosts(userId, page));
    }
}
