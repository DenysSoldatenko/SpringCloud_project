package org.example.postservice.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) representing a comment.
 */
@Schema(description = "DTO representing a comment")
public record CommentDto(

    @Schema(
      description = "Unique identifier of the comment",
      example = "1"
    )
    Long id,

    @NotEmpty(message = "Name should not be null or empty!")
    @Schema(
     description = "Name of the commenter",
     example = "John Doe"
    )
    String name,

    @NotEmpty(message = "Email should not be null or invalid!")
    @Email(message = "Email should not be empty!")
    @Schema(
     description = "Email of the commenter",
     example = "john.doe@example.com"
    )
    String email,

    @NotEmpty(message = "Comment should not be empty!")
    @Size(min = 10, message = "Comment body should not be minimum 10 characters!")
    @Schema(
     description = "Body of the comment",
     example = "This is a comment body."
    )
    String body
) {
}

