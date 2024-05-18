package org.example.postservice.services.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.postservice.dtos.PostDto;
import org.example.postservice.entities.Post;
import org.example.postservice.mappers.PostMapper;
import org.example.postservice.repositories.PostRepository;
import org.example.postservice.services.PostSearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link PostSearchService} interface
 * for searching posts using full-text search with TSV.
 */
@Service
@RequiredArgsConstructor
public class PostSearchServiceImpl implements PostSearchService {

  private final PostRepository postRepository;
  private final PostMapper postMapper;

  @Override
  public List<PostDto> searchByFullTextUsingTsv(String query, int pageNo, int pageSize) {
    PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
    Page<Post> pagedResult = postRepository.searchByFullTextUsingTsv(query, pageRequest);

    return pagedResult.hasContent()
        ? pagedResult.getContent().stream().map(postMapper::toDto).toList()
        : List.of();
  }
}
