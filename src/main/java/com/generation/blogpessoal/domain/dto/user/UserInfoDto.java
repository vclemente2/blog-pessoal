package com.generation.blogpessoal.domain.dto.user;

import com.generation.blogpessoal.domain.dto.post.PostInfoNoJoinDto;
import com.generation.blogpessoal.domain.model.Post;
import com.generation.blogpessoal.domain.model.User;

import java.util.Collections;
import java.util.List;

public record UserInfoDto(
        Long id,
        String name,
        String email,

        String image,

        List<PostInfoNoJoinDto> posts
) {
    public UserInfoDto(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getImage(), generatePostList(user.getPosts()));
    }

    private static List<PostInfoNoJoinDto> generatePostList(List<Post> postList) {
        if (postList == null) {
            return Collections.emptyList();
        }
        return postList.stream().map(PostInfoNoJoinDto::new).toList();
    }
}
