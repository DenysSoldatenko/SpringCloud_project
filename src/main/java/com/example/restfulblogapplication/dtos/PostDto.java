package com.example.restfulblogapplication.dtos;

/**
 * Data Transfer Object (DTO) representing a blog post.
 */
public record PostDto(Long id,
                      String title,
                      String description,
                      String content) {
}
