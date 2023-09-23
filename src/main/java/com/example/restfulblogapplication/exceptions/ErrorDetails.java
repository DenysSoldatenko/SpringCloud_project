package com.example.restfulblogapplication.exceptions;

import java.util.Date;

public record ErrorDetails(Date timestamp,
                           String status,
                           String error,
                           String message,
                           String path) {
}
