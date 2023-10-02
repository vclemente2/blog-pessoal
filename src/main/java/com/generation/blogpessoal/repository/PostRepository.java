package com.generation.blogpessoal.repository;

import com.generation.blogpessoal.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    public List<Post> findAllByTitleContainingIgnoreCase(@Param("title") String title);
}
