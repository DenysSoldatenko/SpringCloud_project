package org.example.appcommon.exceptions.errorhandling;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;

/**
 * Represents details of an error response.
 */
@Schema(description = "Details of an error response")
public record ErrorDetails(

    @Schema(
      description = "Timestamp when the error occurred",
      example = "2024-07-23T12:00:00.600+00:00"
    )
    Date timestamp,

    @Schema(
      description = "HTTP status code of the error",
      example = "404"
    )
    String status,

    @Schema(
      description = "Error type",
      example = "Not Found"
    )
    String error,

    @Schema(
      description = "Detailed error message",
      example = "Comment not found with id: 333"
    )
    String message,

    @Schema(
      description = "Path of the request that caused the error",
      example = "/api/v1/posts/1/comments/333"
    )
    String path
) {
}
