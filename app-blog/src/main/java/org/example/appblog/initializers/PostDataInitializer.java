package org.example.appblog.initializers;

import static java.util.stream.IntStream.range;
import static org.example.appblog.utils.ApplicationConstant.DATA_INITIALIZATION_SUCCESS_MESSAGE;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.appblog.entities.Post;
import org.example.appblog.repositories.PostRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Configuration class for initializing blog data in the database.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PostDataInitializer {

  private final PostRepository postRepository;
  private final PostDataGenerator postDataGenerator;

  /**
   * Initializes blog data in the database.
   */
  @Transactional
  public String initData() {
    int batchSize = 10;
    int totalPosts = 100;

    log.info("Starting post data initialization...");

    range(0, totalPosts / batchSize)
        .parallel()
        .forEach(batchIndex -> {
          try {
            List<Post> postBatch = postDataGenerator.generatePostBatch(batchSize);
            postRepository.saveAll(postBatch);
            log.info("Post batch {} inserted successfully.", batchIndex);
          } catch (Exception e) {
            log.error("Error in post batch {}: {}", batchIndex, e.getMessage(), e);
          }
        });

    log.info("Post data initialization completed successfully.");

    return DATA_INITIALIZATION_SUCCESS_MESSAGE;
  }
}