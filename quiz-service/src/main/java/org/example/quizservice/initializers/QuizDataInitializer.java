package org.example.quizservice.initializers;

import static java.util.stream.IntStream.range;
import static org.example.quizservice.utils.ApplicationConstant.DATA_INITIALIZATION_SUCCESS_MESSAGE;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.quizservice.entities.Quiz;
import org.example.quizservice.repositories.QuizRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Configuration class for initializing Elasticsearch data.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class QuizDataInitializer {

  private final QuizRepository quizRepository;
  private final QuizDataGenerator quizDataGenerator;

  /**
   * Initializes quiz data in the database.
   */
  @Transactional
  public String initData() {
    int batchSize = 10;
    int totalRecords = 100;

    log.info("Starting quiz data initialization...");

    range(0, totalRecords / batchSize)
        .parallel()
        .forEach(batchIndex -> {
          try {
            List<Quiz> quizBatch = quizDataGenerator.generateQuizBatch(batchSize);
            quizRepository.saveAll(quizBatch);
            log.info("Quiz batch {} inserted successfully.", batchIndex);
          } catch (Exception e) {
            log.error("Error in quiz batch {}: {}", batchIndex, e.getMessage(), e);
          }
        });

    log.info("Quiz data initialization completed successfully.");

    return DATA_INITIALIZATION_SUCCESS_MESSAGE;
  }
}