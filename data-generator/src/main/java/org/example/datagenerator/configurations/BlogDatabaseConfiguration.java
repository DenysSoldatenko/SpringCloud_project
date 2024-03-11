package org.example.datagenerator.configurations;

import static java.util.stream.IntStream.range;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.appblog.entities.Post;
import org.example.appblog.repositories.PostRepository;
import org.example.datagenerator.generators.PostGenerator;
import org.springframework.stereotype.Component;

/**
 * Configuration class for initializing blog data in the database.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class BlogDatabaseConfiguration {

  private final PostRepository postRepository;
  private final PostGenerator postGenerator;

  /**
   * Initializes blog data in the database.
   */
  public void initData() {
    int batchSize = 1_000;
    int totalPosts = 100_000;

    range(0, totalPosts / batchSize)
        .parallel()
        .forEach(batchIndex -> {
          try {
            List<Post> postBatch = postGenerator.generatePostBatch(batchSize);
            postRepository.saveAll(postBatch);
            log.info("Blog batch {} inserted successfully.", batchIndex);
          } catch (Exception e) {
            log.error("Error in blog batch {}: {}", batchIndex, e.getMessage(), e);
          }
        });
  }
}