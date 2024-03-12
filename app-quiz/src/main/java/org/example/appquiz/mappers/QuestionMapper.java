package org.example.appquiz.mappers;

import org.example.appquiz.dtos.QuestionDto;
import org.example.appquiz.entities.Question;
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
