package org.example.gatewayservice.configurations;

import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.security.config.web.server.SecurityWebFiltersOrder.AUTHENTICATION;
import static org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers.pathMatchers;
import static reactor.core.publisher.Mono.fromRunnable;

import lombok.extern.slf4j.Slf4j;
import org.example.gatewayservice.jwt.JwtConverter;
import org.example.gatewayservice.jwt.JwtHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity.CsrfSpec;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

/**
 * Configuration class for WebFlux security.
 */
@Slf4j
@Configuration
@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
public class WebSecurityConfig {

  @Value("${jwt.secret}")
  private String secret;

  private final String[] publicRoutes = {
    "/api/v*/auth/**",
    "/v3/api-docs/**",
    "/swagger-ui/**",
    "/swagger-resources/**",
    "/swagger-ui.html",
    "/webjars/**"
  };

  /**
   * Configures the security filters and rules.
   *
   * @param http The ServerHttpSecurity to configure.
   * @param webSecurityManager The WebSecurityManager for handling authentication.
   * @return The configured SecurityWebFilterChain.
   */
  @Bean
  public SecurityWebFilterChain securityWebFilterChain(
      ServerHttpSecurity http, WebSecurityManager webSecurityManager
  ) {

    return http
      .csrf(CsrfSpec::disable)
      .authorizeExchange(
        authorizeExchangeSpec ->
          authorizeExchangeSpec.pathMatchers(OPTIONS).permitAll()
            .pathMatchers(publicRoutes).permitAll()
            .anyExchange().authenticated()
      )
      .exceptionHandling(exceptionHandlingSpec ->
        exceptionHandlingSpec.authenticationEntryPoint(
            (swe, e) -> {
              log.error("IN securityWebFilterChain - unauthorized error: {}", e.getMessage());
              return fromRunnable(() -> swe.getResponse().setStatusCode(UNAUTHORIZED));
            })
          .accessDeniedHandler((swe, e) -> {
            log.error("IN securityWebFilterChain - access denied: {}", e.getMessage());
            return fromRunnable(() -> swe.getResponse().setStatusCode(FORBIDDEN));
          }))
      .addFilterAt(bearerAuthenticationFilter(webSecurityManager), AUTHENTICATION)
      .build();
  }

  private AuthenticationWebFilter bearerAuthenticationFilter(
      WebSecurityManager webSecurityManager
  ) {

    AuthenticationWebFilter bearerAuthenticationFilter
        = new AuthenticationWebFilter(webSecurityManager);

    bearerAuthenticationFilter.setServerAuthenticationConverter(
        new JwtConverter(new JwtHandler(secret)));

    bearerAuthenticationFilter.setRequiresAuthenticationMatcher(pathMatchers("/**"));
    return bearerAuthenticationFilter;
  }
}
