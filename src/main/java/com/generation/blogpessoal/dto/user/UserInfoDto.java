package com.generation.blogpessoal.dto.user;

import com.generation.blogpessoal.model.Post;
import com.generation.blogpessoal.model.User;

import java.util.List;

public record UserInfoDto(
        Long id,
        String name,
        String email,
        String image,
        List<Post> posts
) {
    public UserInfoDto(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getImage(), user.getPosts());
    }
}
