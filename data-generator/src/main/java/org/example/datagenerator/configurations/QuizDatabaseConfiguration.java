package org.example.datagenerator.configurations;

import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.appquiz.entities.Quiz;
import org.example.appquiz.repositories.QuizRepository;
import org.example.datagenerator.generators.QuizGenerator;
import org.springframework.stereotype.Component;

/**
 * Configuration class for initializing Elasticsearch data.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class QuizDatabaseConfiguration {

  private final QuizRepository quizRepository;
  private final QuizGenerator quizGenerator;

  /**
   * Initializes quiz data in the database.
   */
  public void initData() {
    int batchSize = 1_000;
    int totalRecords = 100_000;

    IntStream.range(0, totalRecords / batchSize)
        .parallel()
        .forEach(batchIndex -> {
          try {
            List<Quiz> quizBatch = quizGenerator.generateQuizBatch(batchSize);
            quizRepository.saveAll(quizBatch);
            log.info("Quiz batch {} inserted successfully.", batchIndex);
          } catch (Exception e) {
            log.error("Error in quiz batch {}: {}", batchIndex, e.getMessage(), e);
          }
        });
  }
}