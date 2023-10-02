package com.example.restfulblogapplication.configurations;

import com.example.restfulblogapplication.entities.Quiz;
import com.example.restfulblogapplication.repositories.QuizRepository;
import com.example.restfulblogapplication.utils.QuizGenerator;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for initializing Elasticsearch data.
 */
//@Configuration
@Slf4j
@RequiredArgsConstructor
public class ElasticsearchDataInitializer {

  private final QuizRepository quizRepository;
  private final QuizGenerator quizGenerator;

  /**
   * Initializes data in Elasticsearch using a CommandLineRunner.
   *
   * @return A CommandLineRunner for initializing data.
   */
  @Bean
  public CommandLineRunner initData() {
    return args -> {
      int batchSize = 1_000;
      int totalRecords = 10_000;

      IntStream.range(0, totalRecords / batchSize)
          .parallel()
          .forEach(batchIndex -> {
            try {
              List<Quiz> quizBatch = quizGenerator.generateQuizBatch(batchSize);
              quizRepository.saveAll(quizBatch);
              log.info("Batch {} inserted successfully.", batchIndex);
            } catch (Exception e) {
              log.error("Error in batch {}: {}", batchIndex, e.getMessage(), e);
            }
          });
    };
  }
}