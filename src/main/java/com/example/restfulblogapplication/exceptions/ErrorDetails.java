package com.example.restfulblogapplication.exceptions;

import java.util.Date;

/**
 * Represents details of an error response.
 */
public record ErrorDetails(Date timestamp, String status, String error,
                           String message, String path) {
}
