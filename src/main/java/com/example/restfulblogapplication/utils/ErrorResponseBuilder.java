package com.example.restfulblogapplication.utils;

import com.example.restfulblogapplication.exceptions.errorhandling.ErrorDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Utility class for building error responses.
 */
@Component
@RequiredArgsConstructor
public class ErrorResponseBuilder {

  private final ObjectMapper objectMapper;

  /**
   * Builds an error response and writes it to the HttpServletResponse.
   *
   * @param response      The HttpServletResponse object.
   * @param statusCode    The HTTP status code for the error.
   * @param errorMessage  The detailed error message.
   * @param path          The request path where the error occurred.
   * @throws IOException  If an input or output exception occurs.
   */
  public void buildErrorResponse(HttpServletResponse response, int statusCode,
                                 String errorMessage, String path) throws IOException {
    response.setStatus(statusCode);
    response.setContentType("application/json");

    ErrorDetails errorDetails = new ErrorDetails(
        new Date(),
        String.valueOf(statusCode),
        "Unauthorized",
        errorMessage,
        path
    );
    response.getWriter().write(objectMapper.writeValueAsString(errorDetails));
  }
}
