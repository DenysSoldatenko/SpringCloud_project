package org.example.appgateway.utils;

import static java.lang.Long.parseLong;
import static java.util.List.of;
import static reactor.core.publisher.Mono.justOrEmpty;

import io.jsonwebtoken.Claims;
import java.util.List;
import lombok.experimental.UtilityClass;
import org.example.appgateway.jwt.common.CustomPrincipal;
import org.example.appgateway.jwt.common.VerificationResult;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import reactor.core.publisher.Mono;

/**
 * Factory class for creating Authentication tokens based on JWT verification results.
 */
@UtilityClass
public class AuthTokenFactory {

  /**
   * Creates an Authentication token based on JWT verification results.
   *
   * @param verificationResult The result of JWT verification.
   * @return A Mono emitting the Authentication token if the JWT is valid.
   */
  public static Mono<Authentication> create(VerificationResult verificationResult) {
    Claims claims = verificationResult.claims();
    String subject = claims.getSubject();

    String role = claims.get("role", String.class);
    String username = claims.get("username", String.class);

    List<SimpleGrantedAuthority> authorities = of(new SimpleGrantedAuthority(role));

    Long principalId = parseLong(subject);
    CustomPrincipal principal = new CustomPrincipal(principalId, username);

    return justOrEmpty(new UsernamePasswordAuthenticationToken(principal, null, authorities));
  }
}