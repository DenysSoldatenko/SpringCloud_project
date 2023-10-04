package org.example.appgateway.dtos;

import jakarta.validation.constraints.Email;

/**
 * Data Transfer Object (DTO) for user registration requests.
 */
public record RegisterRequestDto(String firstName, String lastName,
                                 @Email String email, String password) {
}