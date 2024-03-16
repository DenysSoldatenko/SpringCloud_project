package org.example.appquiz.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

/**
 * Data Transfer Object (DTO) representing the result of a quiz.
 */
@Schema(description = "Data Transfer Object representing the result of a quiz")
public record ResultDto(

    @Schema(
      description = "Message indicating the result of the quiz",
      example = "Some answers are incorrect. Please review and try again."
    )
    String message,

    @Schema(
      description = "List of correct answers with question numbers",
      example = "[\"Question 4: C\", \"Question 7: B\", \"Question 9: B\"]"
    )
    List<String> correctAnswers,

     @Schema(
       description = "List of incorrect answers with question numbers",
       example = "[\"Question 1: A\", \"Question 2: B\", \"Question 3: C\", \"Question 5: C\", "
       + "\"Question 6: D\", \"Question 8: D\", \"Question 10: D\"]"
    )
    List<String> incorrectAnswers,

    @Schema(
      description = "Total number of questions in the quiz",
      example = "10"
    )
    int totalQuestions,

    @Schema(
      description = "Number of correct answers provided by the user",
      example = "3"
    )
    int correctCount
) {
}