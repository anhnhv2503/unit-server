package com.anhnhv.unit.server.mapper;

import com.anhnhv.unit.server.dto.response.LikeDTO;
import com.anhnhv.unit.server.entities.Like;

public class LikeMapper {

    public LikeDTO toLikeDTO(Like like) {
        LikeDTO likeDTO = new LikeDTO();
//        likeDTO.setId(like.getId());
//        likeDTO.setPostId(like.getPost().getId());
//        likeDTO.setUserId(like.getUser().getId());
        likeDTO.setLiked(true);
        likeDTO.setMessage("Like added");
        likeDTO.setData(like);
        return likeDTO;
    }
}
