package com.example.restfulblogapplication.services.impl;

import static com.example.restfulblogapplication.utils.ApplicationConstant.POST_NOT_FOUND_MESSAGE;

import com.example.restfulblogapplication.dtos.PostDto;
import com.example.restfulblogapplication.entities.Post;
import com.example.restfulblogapplication.exceptions.PostNotFoundException;
import com.example.restfulblogapplication.mappers.PostMapper;
import com.example.restfulblogapplication.repositories.PostRepository;
import com.example.restfulblogapplication.services.PostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
    PageRequest pageRequest = PageRequest.of(pageNo, pageSize,
        Sort.Direction.fromString(sortDir), sortBy);
    Page<Post> pagedResult = postRepository.findAll(pageRequest);

    return pagedResult.hasContent()
      ? pagedResult.getContent().stream().map(postMapper::toDto).toList()
      : List.of();
  }

  @Override
  public PostDto getPostById(Long id) {
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND_MESSAGE + id));
    return postMapper.toDto(post);
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
