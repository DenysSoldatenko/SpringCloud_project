package org.example.appblog.mappers;

import org.example.appblog.dtos.PostDto;
import org.example.appblog.entities.Post;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * Mapper interface
 * for converting between {@link Post} entities and {@link PostDto} DTOs.
 */
@Component
@Mapper(componentModel = "spring")
public interface PostMapper {

  Post toModel(PostDto postDto);

  PostDto toDto(Post post);
}
