package org.example.appquiz.configurations;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.example.appquiz.entities.Question;

/**
 * Custom deserializer for converting JSON to a {@link Question} object.
 */
public class QuestionDeserializer extends StdDeserializer<Question> {

  public QuestionDeserializer(Class<?> vc) {
    super(vc);
  }

  public QuestionDeserializer() {
    this(null);
  }

  @Override
  public Question deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    JsonNode node = jp.getCodec().readTree(jp);
    Question question = new Question();
    question.setName(node.get("question").asText());
    question.setAnswer(node.get("answer").asText());

    // Extract options into the Map
    Map<String, String> options = new HashMap<>();
    options.put("A", node.get("A").asText());
    options.put("B", node.get("B").asText());
    options.put("C", node.get("C").asText());
    options.put("D", node.get("D").asText());
    question.setOptions(options);

    return question;
  }
}
