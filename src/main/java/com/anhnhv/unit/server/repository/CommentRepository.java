package com.anhnhv.unit.server.repository;

import com.anhnhv.unit.server.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>{

    int countByPostId(Long postId);

    Page<Comment> findAllByPostIdOrderByCreatedAtDesc(Long postId, Pageable pageable);
}
