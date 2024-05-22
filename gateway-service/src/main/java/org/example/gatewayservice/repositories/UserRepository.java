package org.example.gatewayservice.repositories;

import org.example.gatewayservice.entities.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

/**
 * Repository interface for accessing user data.
 */
public interface UserRepository extends R2dbcRepository<User, Long> {

  Mono<User> findByEmail(String username);
}