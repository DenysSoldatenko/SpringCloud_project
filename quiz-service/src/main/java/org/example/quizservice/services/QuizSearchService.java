package org.example.quizservice.services;

import java.util.List;
import org.example.quizservice.dtos.QuizDto;

/**
 * Service interface for searching quizzes.
 */
public interface QuizSearchService {

  List<QuizDto> findByCategory(String category, int pageNo, int pageSize);

  List<QuizDto> findByName(String query, int pageNo, int pageSize);
}
