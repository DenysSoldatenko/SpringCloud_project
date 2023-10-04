package org.example.appcommon.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class representing a not found error
 * for quiz in the Blog API.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class QuizNotFoundException extends RuntimeException {
  public QuizNotFoundException(String message) {
    super(message);
  }
}
