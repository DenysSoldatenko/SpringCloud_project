package org.example.quizservice.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.example.quizservice.entities.Question;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for customizing Jackson ObjectMapper.
 */
@Configuration
public class JacksonConfiguration {

  /**
   * Creates a {@link SimpleModule} with a custom deserializer for {@link Question} entities.
   *
   * @return a configured {@link SimpleModule} instance.
   */
  @Bean
  public SimpleModule questionDeserializerModule() {
    SimpleModule module = new SimpleModule();
    module.addDeserializer(Question.class, new QuestionDeserializer());
    return module;
  }

  /**
   * Configures an {@link ObjectMapper} with the custom {@link SimpleModule}.
   *
   * @param questionDeserializerModule the custom module to register.
   * @return a configured {@link ObjectMapper} instance.
   */
  @Bean
  public ObjectMapper objectMapper(SimpleModule questionDeserializerModule) {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(questionDeserializerModule);
    return objectMapper;
  }
}
