package com.generation.blogpessoal.domain.dto.theme;

import com.generation.blogpessoal.domain.dto.post.PostInfoNoJoinDto;
import com.generation.blogpessoal.domain.model.Post;
import com.generation.blogpessoal.domain.model.Theme;

import java.util.Collections;
import java.util.List;

public record ThemeInfoDto(
        Long id,

        String name,

        List<PostInfoNoJoinDto> posts
) {
    public ThemeInfoDto(Theme theme) {
        this(theme.getId(), theme.getName(), generatePostList(theme.getPosts()));
    }

    private static List<PostInfoNoJoinDto> generatePostList(List<Post> postList) {
        if (postList == null) {
            return Collections.emptyList();
        }
        return postList.stream().map(PostInfoNoJoinDto::new).toList();
    }
}
