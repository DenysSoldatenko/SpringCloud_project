package org.example.appgateway.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class representing authentication-related errors in the Blog API.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AuthException extends RuntimeException {
  public AuthException(String message) {
    super(message);
  }
}
