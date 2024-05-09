package org.example.appblog.exceptions.errorhandling;

import static java.lang.String.valueOf;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.Date;
import org.example.appblog.exceptions.BlogApiException;
import org.example.appblog.exceptions.CommentNotFoundException;
import org.example.appblog.exceptions.PostNotFoundException;
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
   * Handles the exception when a {@link PostNotFoundException} occurs.
   *
   * @param exception  the exception that was thrown.
   * @param webRequest the web request where the exception occurred.
   * @return a ResponseEntity containing details of the error response.
   */
  @ExceptionHandler(PostNotFoundException.class)
  public ResponseEntity<ErrorDetails> handlePostNotFoundException(
      PostNotFoundException exception, WebRequest webRequest
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
   * Handles the exception when a {@link CommentNotFoundException} occurs.
   *
   * @param exception  the exception that was thrown.
   * @param webRequest the web request where the exception occurred.
   * @return a ResponseEntity containing details of the error response.
   */
  @ExceptionHandler(CommentNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleCommentNotFoundException(
      CommentNotFoundException exception, WebRequest webRequest
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
   * Handles the exception when a {@link BlogApiException} occurs.
   *
   * @param exception  the exception that was thrown.
   * @param webRequest the web request where the exception occurred.
   * @return a ResponseEntity containing details of the error response.
   */
  @ExceptionHandler(BlogApiException.class)
  public ResponseEntity<ErrorDetails> handleBlogApiException(
      BlogApiException exception, WebRequest webRequest
  ) {
    ErrorDetails errorDetails = new ErrorDetails(
        new Date(),
        valueOf(BAD_REQUEST.value()),
        BAD_REQUEST.getReasonPhrase(),
        exception.getMessage(),
        webRequest.getDescription(false).substring(4)
    );
    return new ResponseEntity<>(errorDetails, BAD_REQUEST);
  }
}
