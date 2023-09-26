package com.example.restfulblogapplication.exceptions;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

/**
 * Global exception handler for handling specific exceptions
 * and providing consistent error responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Handles the exception when a {@link PostNotFoundException} occurs.
   *
   * @param exception the exception that was thrown.
   * @param webRequest the web request where the exception occurred.
   * @return a ResponseEntity containing details of the error response.
   */
  @ExceptionHandler(PostNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<ErrorDetails> handlePostNotFoundException(
      PostNotFoundException exception, WebRequest webRequest
  ) {

    ErrorDetails errorDetails = new ErrorDetails(
        new Date(),
        String.valueOf(HttpStatus.NOT_FOUND.value()),
        HttpStatus.NOT_FOUND.getReasonPhrase(),
        exception.getMessage(),
        webRequest.getDescription(false).substring(4)
    );

    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  /**
   * Handles the exception when a {@link CommentNotFoundException} occurs.
   *
   * @param exception the exception that was thrown.
   * @param webRequest the web request where the exception occurred.
   * @return a ResponseEntity containing details of the error response.
   */
  @ExceptionHandler(CommentNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleCommentNotFoundException(
      CommentNotFoundException exception, WebRequest webRequest
  ) {
    ErrorDetails errorDetails = new ErrorDetails(
        new Date(),
        String.valueOf(HttpStatus.NOT_FOUND.value()),
        HttpStatus.NOT_FOUND.getReasonPhrase(),
        exception.getMessage(),
        webRequest.getDescription(false).substring(4)
    );
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  /**
   * Handles the exception when a {@link BlogApiException} occurs.
   *
   * @param exception the exception that was thrown.
   * @param webRequest the web request where the exception occurred.
   * @return a ResponseEntity containing details of the error response.
   */
  @ExceptionHandler(BlogApiException.class)
  public ResponseEntity<ErrorDetails> handleBlogApiException(
      BlogApiException exception, WebRequest webRequest
  ) {
    ErrorDetails errorDetails = new ErrorDetails(
        new Date(),
        String.valueOf(HttpStatus.BAD_REQUEST.value()),
        HttpStatus.BAD_REQUEST.getReasonPhrase(),
        exception.getMessage(),
        webRequest.getDescription(false).substring(4)
    );
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handles global exceptions that are not explicitly caught by other methods.
   *
   * @param exception the exception that was thrown.
   * @param webRequest the web request where the exception occurred.
   * @return a ResponseEntity containing details of the error response.
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDetails> handleGlobalException(
      Exception exception, WebRequest webRequest
  ) {
    ErrorDetails errorDetails = new ErrorDetails(
        new Date(),
        String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
        exception.getMessage(),
        webRequest.getDescription(false).substring(4)
    );
    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
