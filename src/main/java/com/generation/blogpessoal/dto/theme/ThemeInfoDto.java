package com.generation.blogpessoal.dto.theme;


import com.generation.blogpessoal.model.Post;

import java.util.List;

public record ThemeInfoDto(
        Long id,

        String name,

        List<Post> posts
) {
}
