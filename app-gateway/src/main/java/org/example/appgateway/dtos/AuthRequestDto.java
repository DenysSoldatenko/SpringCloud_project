package org.example.appgateway.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;

/**
 * Data Transfer Object (DTO) for authentication requests.
 */
@Schema(description = "DTO for authentication requests")
public record AuthRequestDto(
    @Email
    @Schema(
      description = "Email address of the user",
      example = "john.doe@example.com"
    )
    String email,

    @Schema(
      description = "Password of the user",
      example = "password"
    )
    String password
) {
}