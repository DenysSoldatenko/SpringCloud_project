package com.example.restfulblogapplication.services;

import com.example.restfulblogapplication.dtos.PostDto;
import java.util.List;

public interface PostService {
  PostDto createPost(PostDto postDto);

  List<PostDto> getAllPostPaginated(int pageNo, int pageSize, String sortBy, String sortDir);

  PostDto getPostById(Long id);

  PostDto updatePostById(PostDto postDto, Long id);

  void deletePostById(Long id);
}
