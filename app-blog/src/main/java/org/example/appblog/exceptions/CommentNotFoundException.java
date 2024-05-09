package org.example.appblog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class representing a not found error for comments in the Blog API.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CommentNotFoundException extends RuntimeException {
  public CommentNotFoundException(String message) {
    super(message);
  }
}