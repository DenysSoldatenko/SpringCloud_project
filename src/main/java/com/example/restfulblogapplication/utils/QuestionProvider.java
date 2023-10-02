package com.example.restfulblogapplication.utils;

import com.example.restfulblogapplication.entities.Question;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

/**
 * Utility class for providing a list of questions parsed from a JSON file.
 */
@Component
@RequiredArgsConstructor
public class QuestionProvider {

  private final ObjectMapper objectMapper;

  /**
   * Parses JSON data from a file and converts it to a list of questions.
   *
   * @return A list of {@link Question} objects.
   * @throws IOException If there is an error reading or parsing the JSON data.
   */
  public List<Question> parseJsonToQuestionList() throws IOException {
    String jsonData = loadJsonDataFromFile();
    JsonNode jsonNode = objectMapper.readTree(jsonData);
    return objectMapper.convertValue(
      jsonNode,
      objectMapper.getTypeFactory().constructCollectionType(List.class, Question.class)
    );
  }

  private String loadJsonDataFromFile() throws IOException {
    ClassPathResource resource = new ClassPathResource("questions.json");
    return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
  }
}