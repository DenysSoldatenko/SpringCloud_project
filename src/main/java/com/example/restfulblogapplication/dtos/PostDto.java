package com.example.restfulblogapplication.dtos;

public record PostDto(Long id,
                      String title,
                      String description,
                      String content) {
}
