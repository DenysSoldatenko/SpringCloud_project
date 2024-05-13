package org.example.appquiz.services;

import java.util.List;
import org.example.appquiz.dtos.AnswerDto;
import org.example.appquiz.dtos.QuizDto;
import org.example.appquiz.dtos.ResultDto;

/**
 * Service interface for managing quizzes.
 */
public interface QuizService {
  QuizDto createQuiz(QuizDto quizDto);

  List<QuizDto> getAllQuizPaginated(int pageNo, int pageSize, String sortBy, String sortDir);

  QuizDto getQuizById(String id);

  QuizDto updateQuizById(QuizDto quizDto, String id);

  void deleteQuizById(String id);

  ResultDto validateAnswers(AnswerDto answerDto, String id);

  String initializeData();
}
