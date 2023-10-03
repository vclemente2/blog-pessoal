package com.generation.blogpessoal.repository;

import com.generation.blogpessoal.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
    Optional<Theme> findByNameIgnoreCase(String name);

    List<Theme> findAllByNameContainingIgnoreCase(@Param("name") String name);
}
