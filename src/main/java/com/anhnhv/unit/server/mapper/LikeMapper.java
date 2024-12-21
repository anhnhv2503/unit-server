package com.anhnhv.unit.server.mapper;

import com.anhnhv.unit.server.dto.response.LikeDTO;
import com.anhnhv.unit.server.entities.Like;
import org.springframework.stereotype.Component;

@Component
public class LikeMapper {

    public LikeDTO toLikeDTO(Like like) {
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setLiked(true);
        likeDTO.setMessage("Like added");
        likeDTO.setData(like);
        return likeDTO;
    }
}
