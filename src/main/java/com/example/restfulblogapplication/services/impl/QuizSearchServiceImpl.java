package com.example.restfulblogapplication.services.impl;

import com.example.restfulblogapplication.dtos.QuizDto;
import com.example.restfulblogapplication.entities.Quiz;
import com.example.restfulblogapplication.mappers.QuizMapper;
import com.example.restfulblogapplication.repositories.QuizRepository;
import com.example.restfulblogapplication.services.QuizSearchService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link QuizSearchService} interface.
 */
@Service
@RequiredArgsConstructor
public class QuizSearchServiceImpl implements QuizSearchService {

  private final QuizRepository quizRepository;
  private final QuizMapper quizMapper;

  @Override
  public List<QuizDto> findByCategory(String category, int pageNo, int pageSize) {
    Pageable pageable = PageRequest.of(pageNo, pageSize);
    Page<Quiz> pagedResult = quizRepository.findByCategory(category, pageable);

    return pagedResult.hasContent()
      ? pagedResult.getContent().stream().map(quizMapper::toDto).toList()
      : List.of();
  }
}
