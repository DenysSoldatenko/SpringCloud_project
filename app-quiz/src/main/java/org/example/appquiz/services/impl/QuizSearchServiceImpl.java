package org.example.appquiz.services.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.appquiz.dtos.QuizDto;
import org.example.appquiz.entities.Quiz;
import org.example.appquiz.mappers.QuizMapper;
import org.example.appquiz.repositories.QuizRepository;
import org.example.appquiz.services.QuizSearchService;
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

  @Override
  public List<QuizDto> findByName(String category, int pageNo, int pageSize) {
    Pageable pageable = PageRequest.of(pageNo, pageSize);
    Page<Quiz> pagedResult = quizRepository.findByName(category, pageable);

    return pagedResult.hasContent()
      ? pagedResult.getContent().stream().map(quizMapper::toDto).toList()
      : List.of();
  }
}
