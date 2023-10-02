package com.example.restfulblogapplication.dtos.auth;

/**
 * Data Transfer Object (DTO) for authentication requests.
 */
public record AuthenticationRequest(String email, String password) {
}