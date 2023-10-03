package com.example.restfulblogapplication.repositories;

import com.example.restfulblogapplication.entities.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Repository interface for interacting with the Elasticsearch database for Quiz entities.
 */
public interface QuizRepository extends ElasticsearchRepository<Quiz, String> {

  Page<Quiz> findByCategory(String category, Pageable pageable);

  Page<Quiz> findByName(String category, Pageable pageable);
}