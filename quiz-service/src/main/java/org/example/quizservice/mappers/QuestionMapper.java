package org.example.quizservice.mappers;

import org.example.quizservice.dtos.QuestionDto;
import org.example.quizservice.entities.Question;
import org.mapstruct.Mapper;

/**
 * Mapper interface
 * for converting between {@link Question} entities and {@link QuestionDto} DTOs.
 */
@Mapper(componentModel = "spring")
public interface QuestionMapper {

  QuestionDto toDto(Question question);

  Question toModel(QuestionDto questionDto);
}
