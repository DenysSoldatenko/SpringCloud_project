package org.example.postservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class representing a bad request in the Blog API.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BlogApiException extends RuntimeException {
  public BlogApiException(String message) {
    super(message);
  }
}