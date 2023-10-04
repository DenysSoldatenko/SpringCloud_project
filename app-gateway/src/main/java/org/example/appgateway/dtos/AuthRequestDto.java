package org.example.appgateway.dtos;

/**
 * Data Transfer Object (DTO) for authentication requests.
 */
public record AuthRequestDto(String email, String password) {
}