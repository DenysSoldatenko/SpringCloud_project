package org.example.datagenerator.configurations;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for initializing combined data for Blog and Quiz databases.
 */
@Configuration
@RequiredArgsConstructor
public class CombinedDatabaseConfiguration {

  private final BlogDatabaseConfiguration blogDatabaseConfiguration;
  private final QuizDatabaseConfiguration quizDatabaseConfiguration;

  /**
   * Initializes data for both Blog and Quiz databases.
   *
   * @return A CommandLineRunner for initializing combined data.
   */
  @Bean
  public CommandLineRunner combinedInitData() {
    return args -> {
      blogDatabaseConfiguration.initData();
      quizDatabaseConfiguration.initData();
    };
  }
}
