package org.example.appquiz.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Data Transfer Object (DTO) for representing a quiz record.
 */
@Schema(description = "DTO representing a quiz record")
public record QuizDto(

    @Schema(
      description = "Unique identifier of the quiz",
      example = "pqBv25ABmpXDV7gxd443"
    )
    String id,

    @NotEmpty(message = "Quiz name should not be empty!")
    @Schema(
      description = "Name of the quiz",
      example = "General Knowledge Quiz"
    )
    String name,

    @NotEmpty(message = "Quiz category should not be empty!")
    @Schema(
      description = "Category of the quiz",
      example = "General Knowledge"
    )
    String category,

    @NotEmpty(message = "Quiz difficulty should not be empty!")
    @Schema(
      description = "Difficulty level of the quiz",
      example = "Medium"
    )
    String difficulty,

    @NotEmpty(message = "Quiz questions should not be empty!")
    @Schema(
      description = "List of questions in the quiz",
      example = "[{\"name\": \"What is the capital of France?\", "
      + "\"options\": {\"A\": \"Paris\", \"B\": \"London\", \"C\": \"Berlin\", \"D\": \"Madrid\"},"
      + " \"answer\": \"A\"}]"
    )
    List<QuestionDto> questions
) {
}
