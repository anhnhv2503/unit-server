package com.anhnhv.unit.server.services;

import com.anhnhv.unit.server.dto.response.LikeDTO;

public interface ILikeService {

    LikeDTO likePost(Long postId);
}
