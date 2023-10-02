package com.example.restfulblogapplication.mappers;

import com.example.restfulblogapplication.dtos.QuestionDto;
import com.example.restfulblogapplication.entities.Question;
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
