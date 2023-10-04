package org.example.appgateway.jwt.common;

import io.jsonwebtoken.Claims;

/**
 * Data class representing the result of JWT token verification.
 */
public record VerificationResult(Claims claims, String token) {
}