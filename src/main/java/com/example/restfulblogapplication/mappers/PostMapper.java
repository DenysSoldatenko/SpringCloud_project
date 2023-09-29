package com.example.restfulblogapplication.mappers;

import com.example.restfulblogapplication.dtos.PostDto;
import com.example.restfulblogapplication.entities.Post;
import org.mapstruct.Mapper;

/**
 * Mapper interface
 * for converting between {@link Post} entities and {@link PostDto} DTOs.
 */
@Mapper(componentModel = "spring")
public interface PostMapper {

  Post toModel(PostDto postDto);

  PostDto toDto(Post post);
}
