package com.example.restfulblogapplication.dtos;

/**
 * Data Transfer Object (DTO) for user registration requests.
 */
public record RegisterRequest(String firstName, String lastName,
                              String email, String password) {
}