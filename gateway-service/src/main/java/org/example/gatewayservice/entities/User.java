package org.example.gatewayservice.entities;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Entity representing a user in the system.
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("users")
public class User {

  @Id
  private Long id;

  private String firstName;

  private String lastName;

  @Email
  private String email;

  private String password;

  private Role role;

  @ToString.Include(name = "password")
  private String maskPassword() {
    return "********";
  }
}

