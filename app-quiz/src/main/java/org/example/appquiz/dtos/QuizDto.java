package org.example.appquiz.dtos;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Data Transfer Object (DTO) for representing a quiz record.
 */
public record QuizDto(

    String id,

    @NotEmpty(message = "Quiz name should not be empty!")
    String name,

    @NotEmpty(message = "Quiz category should not be empty!")
    String category,

    @NotEmpty(message = "Quiz difficulty should not be empty!")
    String difficulty,

    @NotEmpty(message = "Quiz questions should not be empty!")
    List<QuestionDto> questions
) {
}
