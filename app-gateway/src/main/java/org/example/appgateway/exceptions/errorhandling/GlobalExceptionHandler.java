package org.example.appgateway.exceptions.errorhandling;

import static java.lang.String.valueOf;
import static java.util.stream.Collectors.joining;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.Date;

import org.example.appgateway.exceptions.AuthException;
import org.example.appgateway.exceptions.ConflictException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
   * Handles the exception when a {@link AuthException} occurs.
   *
   * @param exception  the exception that was thrown.
   * @param webRequest the web request where the exception occurred.
   * @return a ResponseEntity containing details of the error response.
   */
  @ExceptionHandler(AuthException.class)
  public ResponseEntity<ErrorDetails> handleAuthException(
      AuthException exception, WebRequest webRequest
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

  /**
   * Handles the exception when a {@link ConflictException} occurs.
   *
   * @param exception  the exception that was thrown.
   * @param webRequest the web request where the exception occurred.
   * @return a ResponseEntity containing details of the error response.
   */
  @ExceptionHandler(ConflictException.class)
  public ResponseEntity<ErrorDetails> handleConflictException(
      ConflictException exception, WebRequest webRequest
  ) {
    ErrorDetails errorDetails = new ErrorDetails(
        new Date(),
        valueOf(CONFLICT.value()),
        CONFLICT.getReasonPhrase(),
        exception.getMessage(),
        webRequest.getDescription(false).substring(4)
    );
    return new ResponseEntity<>(errorDetails, CONFLICT);
  }

  /**
   * Handles global exceptions that are not explicitly caught by other methods.
   *
   * @param exception  the exception that was thrown.
   * @param webRequest the web request where the exception occurred.
   * @return a ResponseEntity containing details of the error response.
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDetails> handleGlobalException(
      Exception exception, WebRequest webRequest
  ) {
    ErrorDetails errorDetails = new ErrorDetails(
        new Date(),
        valueOf(INTERNAL_SERVER_ERROR.value()),
        INTERNAL_SERVER_ERROR.getReasonPhrase(),
        exception.getMessage(),
        webRequest.getDescription(false).substring(4)
    );
    return new ResponseEntity<>(errorDetails, INTERNAL_SERVER_ERROR);
  }

  /**
   * Handles validation exceptions that occur due to method argument validation failures.
   *
   * @param exception  the MethodArgumentNotValidException that was thrown.
   * @param webRequest the web request where the exception occurred.
   * @return a ResponseEntity containing details of the error response.
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorDetails> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException exception, WebRequest webRequest
  ) {

    String errorMessage = exception.getBindingResult().getFieldErrors().stream()
        .map(FieldError::getDefaultMessage)
        .collect(joining(", "));

    ErrorDetails errorDetails = new ErrorDetails(
        new Date(),
        valueOf(BAD_REQUEST.value()),
        BAD_REQUEST.getReasonPhrase(),
        errorMessage,
        webRequest.getDescription(false).substring(4)
    );

    return new ResponseEntity<>(errorDetails, BAD_REQUEST);
  }
}
