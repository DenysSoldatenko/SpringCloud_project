package org.example.appgateway.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.appgateway.entities.User;
import org.example.appgateway.jwt.common.TokenDetails;
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
    String token = Jwts.builder()
        .claims(claims)
        .issuer(issuer)
        .subject(subject)
        .issuedAt(createdDate)
        .id(UUID.randomUUID().toString())
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
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}