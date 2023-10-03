package com.example.restfulblogapplication.services;

import com.example.restfulblogapplication.dtos.QuizDto;
import java.util.List;

/**
 * Service interface for searching quizzes.
 */
public interface QuizSearchService {

  List<QuizDto> findByCategory(String category, int pageNo, int pageSize);

  List<QuizDto> findByName(String query, int pageNo, int pageSize);
}
