package com.generation.blogpessoal.domain.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CompleteUpdateUserDto(
        String name,

        String email,

        String password,

        String image,

        @NotNull
        Long id,

        @NotBlank
        String token
) {
    public CompleteUpdateUserDto(Long id, String token) {
        this(null, null, null, null, id, token);
    }

    public CompleteUpdateUserDto(BodyDataUpdateUserDto bodyDto, Long id, String token) {
        this(bodyDto.name(), bodyDto.email(), bodyDto.password(), bodyDto.image(), id, token);
    }
}
