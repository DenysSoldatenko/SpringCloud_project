package org.example.appquiz.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Data Transfer Object (DTO) for representing a user's answer to a quiz question.
 */
@Schema(description = "DTO representing a user's answer to a quiz question")
public record AnswerDto(

    @NotEmpty(message = "User answer should not be empty!")
    @Schema(
      description = "List of user answers",
      example = "[\"A\", \"B\", \"C\", \"C\", \"C\", \"D\", \"B\", \"D\", \"B\", \"D\"]"
    )
    List<String> userAnswers
) {
}
