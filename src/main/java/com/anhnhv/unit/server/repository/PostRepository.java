package com.anhnhv.unit.server.repository;

import com.anhnhv.unit.server.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByUserIdOrderByCreatedAtDesc(Long userid, Pageable pageable);

}
