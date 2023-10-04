package org.example.appquiz.mappers;

import org.example.appquiz.dtos.QuizDto;
import org.example.appquiz.entities.Quiz;
import org.mapstruct.Mapper;

/**
 * Mapper interface
 * for converting between {@link Quiz} entities and {@link QuizDto} DTOs.
 */
@Mapper(componentModel = "spring")
public interface QuizMapper {

  QuizDto toDto(Quiz quiz);

  Quiz toModel(QuizDto quizDto);
}
