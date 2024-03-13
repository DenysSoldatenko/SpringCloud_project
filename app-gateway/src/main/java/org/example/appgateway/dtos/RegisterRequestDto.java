package org.example.appgateway.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;

/**
 * Data Transfer Object (DTO) for user registration requests.
 */
@Schema(description = "DTO for user registration requests")
public record RegisterRequestDto(
    @Schema(
      description = "First name of the user",
      example = "John"
    )
    String firstName,

    @Schema(
      description = "Last name of the user",
      example = "Doe"
    )
    String lastName,

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