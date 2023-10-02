package com.example.restfulblogapplication.entities;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Represents a quiz entity.
 */
@Data
@Builder
@Document(indexName = "quizzes")
public class Quiz {

  @Id
  private String id;

  private String name;

  private String category;

  private String difficulty;

  private List<Question> questions;
}
