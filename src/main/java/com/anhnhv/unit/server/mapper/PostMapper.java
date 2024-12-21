package com.anhnhv.unit.server.mapper;

import com.anhnhv.unit.server.dto.response.PostDTO;
import com.anhnhv.unit.server.entities.Post;
import com.anhnhv.unit.server.entities.PostMedia;
import com.anhnhv.unit.server.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostMapper {


    public PostDTO toPostDTO(Post post, boolean isLiked, int likesCount, int commentsCount){
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setAuthor(post.getUser().getFirstName() + " " + post.getUser().getLastName());
        postDTO.setAuthorId(post.getUser().getId());
        postDTO.setAvatar(post.getUser().getAvatar());
        postDTO.setContent(post.getContent());
        List<String> mediaUrls = post.getPostMedia().stream()
                .map(PostMedia::getUrl)
                .collect(Collectors.toList());
        postDTO.setMedia(mediaUrls);
        postDTO.setLikesCount(likesCount);
        postDTO.setCommentsCount(commentsCount);
        postDTO.setCreatedAt(post.getCreatedAt().toString());
        postDTO.setLiked(isLiked);
        return postDTO;
    }

    public Page<PostDTO> postDTOS(Page<Post> posts){
        return null;
    }

}
