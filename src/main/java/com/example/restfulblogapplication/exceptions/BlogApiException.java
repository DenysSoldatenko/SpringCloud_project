package com.example.restfulblogapplication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BlogApiException extends RuntimeException {
  public BlogApiException(String message) {
    super(message);
  }
}