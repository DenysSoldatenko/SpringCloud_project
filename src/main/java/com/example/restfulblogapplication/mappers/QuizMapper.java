package com.example.restfulblogapplication.mappers;

import com.example.restfulblogapplication.dtos.QuizDto;
import com.example.restfulblogapplication.entities.Quiz;
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
