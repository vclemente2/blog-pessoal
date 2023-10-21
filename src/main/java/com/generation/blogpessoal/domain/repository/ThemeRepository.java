package com.generation.blogpessoal.domain.repository;

import com.generation.blogpessoal.domain.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
    List<Theme> findAllByNameContainingIgnoreCase(@Param("name") String name);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);
}
