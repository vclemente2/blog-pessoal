package com.generation.blogpessoal.dto.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdatePostDto(
        @NotNull(message = "The id field is required.")
        Long id,

        @NotBlank(message = "The title field is required.")
        @Size(min = 5, max = 100, message = "The title field must be between 5 and 100 characters long.")
        String title,

        @NotBlank(message = "The content field is required.")
        @Size(min = 10, max = 1000, message = "The content field must be between 10 and 1000 characters long.")
        String content,

        @NotNull(message = "The theme id field is required.")
        Long themeId,

        @NotNull(message = "The user id field is required.")
        Long userId
) {
}
