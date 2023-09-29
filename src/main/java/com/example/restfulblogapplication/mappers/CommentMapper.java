package com.example.restfulblogapplication.mappers;

import com.example.restfulblogapplication.dtos.CommentDto;
import com.example.restfulblogapplication.entities.Comment;
import org.mapstruct.Mapper;

/**
 * Mapper interface
 * for converting between {@link Comment} entities and {@link CommentDto} DTOs.
 */
@Mapper(componentModel = "spring")
public interface CommentMapper {

  Comment toModel(CommentDto commentDto);

  CommentDto toDto(Comment post);
}
