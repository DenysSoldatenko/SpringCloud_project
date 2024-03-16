package org.example.appquiz.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import java.util.Map;

/**
 * Data Transfer Object (DTO) for representing a quiz question.
 */
@Schema(description = "DTO representing a quiz question")
public record QuestionDto(

    @NotEmpty(message = "Question name should not be empty!")
    @Schema(
      description = "The text of the question",
      example = "What is the capital of France?"
    )
    String name,

    @NotEmpty(message = "Question options should not be empty!")
    @Schema(
      description = "A map of options for the question, "
      + "where the key is the option identifier and the value is the option text",
      example = "{\"A\": \"Paris\", \"B\": \"London\", \"C\": \"Berlin\", \"D\": \"Madrid\"}"
    )
    Map<String, String> options,

    @NotEmpty(message = "Question answer should not be empty!")
    @Schema(
      description = "The correct answer for the question",
      example = "A"
    )
    String answer
) {
}

