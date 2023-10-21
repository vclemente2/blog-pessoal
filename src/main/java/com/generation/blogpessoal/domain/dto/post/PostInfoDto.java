package com.generation.blogpessoal.domain.dto.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.generation.blogpessoal.domain.dto.theme.ThemeInfoDto;
import com.generation.blogpessoal.domain.dto.user.UserInfoDto;
import com.generation.blogpessoal.domain.model.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record PostInfoDto(
        @NotNull(message = "The id field is required.")
        Long id,

        @NotBlank(message = "The title field is required.")
        @Size(min = 5, max = 100, message = "The title field must be between 5 and 100 characters long.")
        String title,

        @NotBlank(message = "The content field is required.")
        @Size(min = 10, max = 1000, message = "The content field must be between 10 and 1000 characters long.")
        String content,

        @NotNull
        LocalDateTime date,

        @JsonIgnoreProperties("posts")
        ThemeInfoDto theme,

        @JsonIgnoreProperties("posts")
        UserInfoDto user
) {
    public PostInfoDto(Post post) {
        this(post.getId(), post.getTitle(), post.getContent(), post.getDate(), new ThemeInfoDto(post.getTheme()), new UserInfoDto(post.getUser()));
    }
}
