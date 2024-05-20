package org.example.quizservice.exceptions.errorhandling;

import static java.lang.String.valueOf;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.Date;
import org.example.quizservice.exceptions.QuizNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Global exception handler for handling specific exceptions
 * and providing consistent error responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Handles the exception when a {@link QuizNotFoundException} occurs.
   *
   * @param exception  the exception that was thrown.
   * @param webRequest the web request where the exception occurred.
   * @return a ResponseEntity containing details of the error response.
   */
  @ExceptionHandler(QuizNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleQuizNotFoundException(
      QuizNotFoundException exception, WebRequest webRequest
  ) {
    ErrorDetails errorDetails = new ErrorDetails(
        new Date(),
        valueOf(NOT_FOUND.value()),
        NOT_FOUND.getReasonPhrase(),
        exception.getMessage(),
        webRequest.getDescription(false).substring(4)
    );
    return new ResponseEntity<>(errorDetails, NOT_FOUND);
  }
}
