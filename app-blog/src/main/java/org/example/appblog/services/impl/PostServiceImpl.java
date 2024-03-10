package org.example.appblog.services.impl;

import static org.example.appcommon.utils.ApplicationConstant.POST_NOT_FOUND_MESSAGE;
import static org.springframework.data.domain.Sort.Direction.fromString;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.appblog.dtos.PostDto;
import org.example.appblog.entities.Post;
import org.example.appblog.mappers.PostMapper;
import org.example.appblog.repositories.PostRepository;
import org.example.appblog.services.PostService;
import org.example.appcommon.exceptions.PostNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing blog posts in the application.
 */
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;
  private final PostMapper postMapper;

  @Override
  public PostDto createPost(PostDto postDto) {
    Post post = postMapper.toModel(postDto);
    postRepository.save(post);
    return postMapper.toDto(post);
  }

  @Override
  public List<PostDto> getAllPostPaginated(int pageNo, int pageSize,
                                           String sortBy, String sortDir) {
    PageRequest pageRequest = PageRequest.of(pageNo, pageSize, fromString(sortDir), sortBy);
    Page<PostDto> pagedResult = postRepository.findAllPosts(pageRequest);

    return pagedResult.hasContent()
      ? pagedResult.getContent().stream().toList()
      : List.of();
  }

  @Override
  public PostDto getPostById(Long id) {
    return postRepository.findPostDtoById(id)
        .orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND_MESSAGE + id));
  }

  @Override
  public PostDto updatePostById(PostDto postDto, Long id) {
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
