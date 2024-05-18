package org.example.postservice.mappers;

import org.example.postservice.dtos.CommentDto;
import org.example.postservice.entities.Comment;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * Mapper interface
 * for converting between {@link Comment} entities and {@link CommentDto} DTOs.
 */
@Component
@Mapper(componentModel = "spring")
public interface CommentMapper {

  Comment toModel(CommentDto commentDto);

  CommentDto toDto(Comment comment);
}
