package org.example.gatewayservice.jwt;

import static io.jsonwebtoken.Jwts.builder;
import static io.jsonwebtoken.io.Decoders.BASE64;
import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;
import static java.util.UUID.randomUUID;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.gatewayservice.entities.User;
import org.example.gatewayservice.jwt.common.TokenDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Service class for handling JWT generation and related operations.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtService {

  @Value("${jwt.secret}")
  private String secretKey;

  @Value("${jwt.expiration}")
  private Integer expirationInSeconds;

  @Value("${jwt.issuer}")
  private String issuer;

  /**
   * Generates a JWT for the given user.
   *
   * @param user The user for whom the token is generated.
   * @return The generated TokenDetails containing the JWT.
   */
  public TokenDetails generateToken(User user) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("role", user.getRole());
    claims.put("username", user.getEmail());
    return generateToken(claims, user.getId().toString());
  }

  private TokenDetails generateToken(Map<String, Object> claims, String subject) {
    long expirationTimeInMillis = expirationInSeconds * 1000L;
    Date expirationDate = new Date(new Date().getTime() + expirationTimeInMillis);

    return generateToken(expirationDate, claims, subject);
  }

  private TokenDetails generateToken(Date expirationDate,
                                     Map<String, Object> claims, String subject) {
    Date createdDate = new Date();
    String token = builder()
        .claims(claims)
        .issuer(issuer)
        .subject(subject)
        .issuedAt(createdDate)
        .id(randomUUID().toString())
        .expiration(expirationDate)
        .signWith(getSignInKey())
        .compact();

    return TokenDetails.builder()
      .token(token)
      .issuedAt(createdDate)
      .expiresAt(expirationDate)
      .build();
  }

  private SecretKey getSignInKey() {
    byte[] keyBytes = BASE64.decode(secretKey);
    return hmacShaKeyFor(keyBytes);
  }
}