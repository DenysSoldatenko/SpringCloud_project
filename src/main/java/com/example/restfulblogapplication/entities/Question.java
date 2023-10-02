package com.example.restfulblogapplication.entities;

import com.example.restfulblogapplication.utils.QuestionDeserializer;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;

/**
 * Represents a quiz question entity.
 */
@Data
@JsonDeserialize(using = QuestionDeserializer.class)
public class Question {

  private String name;
  private Map<String, String> options = new HashMap<>(); // Unified map for options A, B, C, D
  private String answer;

  @JsonAnySetter
  public void setOptions(String key, String value) {
    options.put(key, value);
  }
}
