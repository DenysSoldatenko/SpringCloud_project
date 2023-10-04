package org.example.appblog.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) representing a comment.
 */
public record CommentDto(

    Long id,

    @NotEmpty(message = "Name should not be null or empty!")
    String name,

    @NotEmpty(message = "Email should not be null or invalid!")
    @Email(message = "Email should not be empty!")
    String email,

    @NotEmpty(message = "Comment should not be empty!")
    @Size(min = 10, message = "Comment body should not be minimum 10 characters!")
    String body
) {
}

