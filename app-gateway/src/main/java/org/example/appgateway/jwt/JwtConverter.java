package org.example.appgateway.jwt;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static reactor.core.publisher.Mono.justOrEmpty;

import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.example.appgateway.utils.AuthTokenFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Converts a ServerWebExchange into an Authentication token based on a JWT.
 */
@RequiredArgsConstructor
public class JwtConverter implements ServerAuthenticationConverter {
  private static final String BEARER_PREFIX = "Bearer ";
  private static final Function<String, Mono<String>> getBearerValue
      = authValue -> justOrEmpty(authValue.substring(BEARER_PREFIX.length()));

  private final JwtHandler jwtHandler;

  @Override
  public Mono<Authentication> convert(ServerWebExchange exchange) {
    return extractHeader(exchange)
      .flatMap(getBearerValue)
      .flatMap(jwtHandler::check)
      .flatMap(AuthTokenFactory::create);
  }

  private Mono<String> extractHeader(ServerWebExchange exchange) {
    return justOrEmpty(exchange.getRequest()
      .getHeaders()
      .getFirst(AUTHORIZATION));
  }
}