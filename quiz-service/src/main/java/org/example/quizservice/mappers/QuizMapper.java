package org.example.quizservice.mappers;

import org.example.quizservice.dtos.QuizDto;
import org.example.quizservice.entities.Quiz;
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
