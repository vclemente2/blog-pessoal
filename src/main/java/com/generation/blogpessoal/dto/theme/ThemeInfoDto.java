package com.generation.blogpessoal.dto.theme;


import com.generation.blogpessoal.model.Post;
import com.generation.blogpessoal.model.Theme;

import java.util.List;

public record ThemeInfoDto(
        Long id,

        String name,

        List<Post> posts
) {
    public ThemeInfoDto(Theme theme) {
        this(theme.getId(), theme.getName(), theme.getPosts());
    }
}
