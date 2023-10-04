package org.example.appblog.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) representing a blog post.
 */
public record PostDto(

    Long id,

    @NotEmpty(message = "Post title should not be empty!")
    @Size(min = 5, message = "Post title should have at least 5 characters!")
    String title,

    @NotEmpty(message = "Post description should not be empty!")
    @Size(min = 10, message = "Post description should have at least 10 characters!")
    String description,

    @NotEmpty(message = "Post content should not be empty!")
    String content
) {
}
