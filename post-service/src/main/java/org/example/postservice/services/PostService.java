package org.example.postservice.services;

import java.util.List;
import org.example.postservice.dtos.PostDto;

/**
 * Service interface for managing blog posts in the application.
 */
public interface PostService {
  PostDto createPost(PostDto postDto);

  List<PostDto> getAllPostPaginated(int pageNo, int pageSize, String sortBy, String sortDir);

  PostDto getPostById(Long id);

  PostDto updatePostById(PostDto postDto, Long id);

  void deletePostById(Long id);

  String initializeData();
}
