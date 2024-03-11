package org.example.datagenerator.configurations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for initializing combined data for Blog and Quiz databases.
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class CombinedDatabaseConfiguration {

  private final ConfigurableApplicationContext applicationContext;
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
      log.info("Starting combined data initialization...");
      blogDatabaseConfiguration.initData();
      log.info("Blog database initialization completed successfully.");
      quizDatabaseConfiguration.initData();
      log.info("Quiz database initialization completed successfully.");
      applicationContext.close();
    };
  }
}
