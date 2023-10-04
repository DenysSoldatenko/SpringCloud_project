package org.example.appquiz.repositories;

import org.example.appquiz.entities.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for interacting with the Elasticsearch database for Quiz entities.
 */
@Repository
public interface QuizRepository extends ElasticsearchRepository<Quiz, String> {

  Page<Quiz> findByCategory(String category, Pageable pageable);

  Page<Quiz> findByName(String category, Pageable pageable);
}