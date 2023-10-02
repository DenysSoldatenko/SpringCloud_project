package com.example.restfulblogapplication.services;

import com.example.restfulblogapplication.dtos.QuizDto;
import java.util.List;

/**
 * Service interface for managing quizzes.
 */
public interface QuizService {
  QuizDto createQuiz(QuizDto quizDto);

  List<QuizDto> getAllQuizPaginated(int pageNo, int pageSize, String sortBy, String sortDir);

  QuizDto getQuizById(String id);

  QuizDto updateQuizById(QuizDto quizDto, String id);

  void deleteQuizById(String id);
}
