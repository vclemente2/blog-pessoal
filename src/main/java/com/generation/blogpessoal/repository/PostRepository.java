package com.generation.blogpessoal.repository;

import com.generation.blogpessoal.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByTitleContainingIgnoreCase(@Param("title") String title);

    List<Post> findAllByContentContainingIgnoreCase(@Param("content") String content);
    List<Post> findAllByTitleContainingAndContentContainingIgnoreCase(@Param("title") String title, @Param("Content") String content);
}
