package org.example.quizservice.initializers;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.readString;
import static org.springframework.util.StreamUtils.copyToString;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.quizservice.entities.Question;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

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
    if (!resource.exists()) {
      File file = new File("/app/questions.json");
      return readString(file.toPath());
    }
    return copyToString(resource.getInputStream(), UTF_8);
  }
}