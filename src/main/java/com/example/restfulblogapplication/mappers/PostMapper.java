package com.example.restfulblogapplication.mappers;

import com.example.restfulblogapplication.dtos.PostDto;
import com.example.restfulblogapplication.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

  @Mapping(target = "comments", ignore = true)
  Post toModel(PostDto postDto);

  //@Mapping(source="comments",target = "comments")
  PostDto toDto(Post post);
}
