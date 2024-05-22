package org.example.gatewayservice.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.Builder;

/**
 * Data Transfer Object (DTO) for authentication response.
 */
@Builder
@JsonNaming(SnakeCaseStrategy.class)
@Schema(description = "DTO for authentication response")
public record AuthResponseDto(

    @Schema(
      description = "ID of the user",
      example = "1"
    )
    Long userId,

    @Schema(
      description = "JWT token for authentication",
      example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    )
    String token,

    @Schema(
      description = "Timestamp when the token was issued",
      example = "2024-07-23T12:00:00.600+00:00"
    )
    Date issuedAt,

    @Schema(
      description = "Timestamp when the token expires",
      example = "2024-07-23T12:00:00.600+00:00"
    )
    Date expiresAt
) {
}