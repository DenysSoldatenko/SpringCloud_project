package org.example.quizservice.initializers;

import static java.util.stream.IntStream.range;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.example.quizservice.entities.Question;
import org.example.quizservice.entities.Quiz;
import org.springframework.stereotype.Component;

/**
 * Utility class for generating batches of quizzes.
 */
@Component
@RequiredArgsConstructor
public class QuizDataGenerator {

  private final Faker faker = new Faker(new Locale("en"));
  private final QuestionProvider questionProvider;

  /**
   * Generates a batch of quizzes.
   *
   * @param batchSize The size of the batch to generate.
   * @return A list of quizzes.
   * @throws IOException If there are error reading questions.
   */
  public List<Quiz> generateQuizBatch(int batchSize) throws IOException {
    List<Question> questions = questionProvider.parseJsonToQuestionList();

    return range(0, batchSize)
      .mapToObj(i -> generateQuiz(questions))
      .toList();
  }

  private Quiz generateQuiz(List<Question> questions) {
    int totalQuestions = questions.size();
    int numQuestionsToPick = faker.number().numberBetween(10, 20);

    int startIndex = faker.number().numberBetween(0, totalQuestions - numQuestionsToPick);
    List<Question> questionList = questions.subList(startIndex, startIndex + numQuestionsToPick);

    return Quiz.builder()
      .name(generateRandomSentence())
      .category(faker.book().genre())
      .difficulty(faker.options().option("Easy", "Medium", "Hard"))
      .questions(questionList)
      .build();
  }

  private String generateRandomSentence() {
    String sentence = faker.harryPotter().quote();
    sentence = sentence.endsWith(".") ? sentence.substring(0, sentence.length() - 1) : sentence;
    return sentence.substring(0, 1).toUpperCase() + sentence.substring(1) + "?";
  }
}