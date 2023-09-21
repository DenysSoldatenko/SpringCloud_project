package com.example.restfulblogapplication.mappers;

import com.example.restfulblogapplication.dtos.PostDto;
import com.example.restfulblogapplication.entities.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {

  Post toModel(PostDto car);

  PostDto toDto(Post person);
}
