package com.anhnhv.unit.server.repository;

import com.anhnhv.unit.server.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
