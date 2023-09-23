package com.example.restfulblogapplication.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CommentDto(

        Long id,

        @NotEmpty(message = "Name should not be null or empty!")
        String name,

        @NotEmpty(message = "Email should not be null or empty!")
        @Email
        String email,

        @NotEmpty
        @Size(min = 10, message = "Comment body should not be minimum 10 characters!")
        String body
) {
}

