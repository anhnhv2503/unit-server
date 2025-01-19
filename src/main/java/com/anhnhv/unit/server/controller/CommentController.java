package com.anhnhv.unit.server.controller;

import com.anhnhv.unit.server.services.ICommentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentController {

    ICommentService commentService;

    @PostMapping("/create/{postId}")
    public ResponseEntity<?> createComment(@PathVariable Long postId,@RequestParam String content) {
        return ResponseEntity.ok(commentService.createComment(postId, content));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<?> getCommentByPost(@PathVariable Long postId, @RequestParam int page) {
        return ResponseEntity.ok(commentService.getAllCommentsByPostId(postId, page));
    }
}
