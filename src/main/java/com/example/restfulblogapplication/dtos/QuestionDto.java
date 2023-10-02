package com.example.restfulblogapplication.dtos;

import jakarta.validation.constraints.NotEmpty;
import java.util.Map;

/**
 * Data Transfer Object (DTO) for representing a quiz question.
 */
public record QuestionDto(

    @NotEmpty(message = "Question name should not be empty!")
    String name,

    @NotEmpty(message = "Question options should not be empty!")
    Map<String, String> options,

    @NotEmpty(message = "Question answer should not be empty!")
    String answer
) {
}

