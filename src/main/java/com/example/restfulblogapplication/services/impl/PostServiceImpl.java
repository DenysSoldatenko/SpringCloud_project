package com.example.restfulblogapplication.services.impl;

import com.example.restfulblogapplication.dtos.PostDto;
import com.example.restfulblogapplication.entities.Post;
import com.example.restfulblogapplication.exceptions.PostNotFoundException;
import com.example.restfulblogapplication.mappers.PostMapper;
import com.example.restfulblogapplication.repositories.PostRepository;
import com.example.restfulblogapplication.services.PostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

  private static final String POST_NOT_FOUND_MESSAGE = "Post not found with id: ";

  private final PostRepository postRepository;
  private final PostMapper postMapper;

  @Override
  public PostDto createPost(PostDto postDto) {
    Post post = postMapper.toModel(postDto);
    postRepository.save(post);
    return postMapper.toDto(post);
  }

  @Override
  public List<PostDto> getAllPosts() {
    List<Post> posts = postRepository.findAll();
    return posts.stream().map(postMapper::toDto).toList();
  }

  @Override
  public PostDto getPostById(Long id) {
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND_MESSAGE + id));
    return postMapper.toDto(post);
  }

  @Override
  public PostDto updatePost(PostDto postDto, Long id) {
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND_MESSAGE + id));

    post.setTitle(postDto.title());
    post.setDescription(postDto.description());
    post.setContent(postDto.content());

    Post updatedPost = postRepository.save(post);
    return postMapper.toDto(updatedPost);
  }

  @Override
  public void deletePostById(Long id) {
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND_MESSAGE + id));
    postRepository.delete(post);
  }
}
