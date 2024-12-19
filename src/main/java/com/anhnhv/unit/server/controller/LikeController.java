package com.anhnhv.unit.server.controller;

import com.anhnhv.unit.server.dto.response.LikeDTO;
import com.anhnhv.unit.server.services.ILikeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/like")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LikeController {

    ILikeService likeService;

    @PostMapping("/like-post/{postId}")
    public ResponseEntity<LikeDTO> likePost(@PathVariable Long postId) {
        return ResponseEntity.ok(likeService.likePost(postId));
    }
}
