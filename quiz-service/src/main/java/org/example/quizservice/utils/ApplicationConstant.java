package org.example.quizservice.utils;

import lombok.experimental.UtilityClass;

/**
 * Utility class for holding application constants.
 */
@UtilityClass
public class ApplicationConstant {

  public static final String QUIZ_NOT_FOUND_MESSAGE = "Quiz not found with id: ";

  public static final String CONGRATULATIONS_MESSAGE = "Congratulations! All answers are correct!";
  public static final String TRY_AGAIN_MESSAGE = "Some answers are incorrect. "
      + "Please review and try again.";

  public static final String DATA_INITIALIZATION_SUCCESS_MESSAGE
      = "Data initialization completed successfully!";
}
