package org.example.appquiz.dtos;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Data Transfer Object (DTO) for representing a user's answer to a quiz question.
 */
public record AnswerDto(
    @NotEmpty(message = "User answer should not be empty!")
    List<String> userAnswers
) {
}
