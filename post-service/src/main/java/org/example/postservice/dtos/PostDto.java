package org.example.postservice.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) representing a blog post.
 */
@Schema(description = "Data Transfer Object representing a blog post")
public record PostDto(

    @Schema(
      description = "Unique identifier of the post",
      example = "1"
    )
    Long id,

    @NotEmpty(message = "Post title should not be empty!")
    @Size(min = 5, message = "Post title should have at least 5 characters!")
    @Schema(
      description = "Title of the post",
      example = "My First Blog Post"
    )
    String title,

    @NotEmpty(message = "Post description should not be empty!")
    @Size(min = 10, message = "Post description should have at least 10 characters!")
    @Schema(
      description = "Description of the post",
      example = "This is a description of my first blog post."
    )
    String description,

    @NotEmpty(message = "Post content should not be empty!")
    @Schema(
      description = "Content of the post",
      example = "This is the content of my first blog post."
    )
    String content
) {
}
