package com.example.restfulblogapplication.controllers;

import com.example.restfulblogapplication.dtos.PostDto;
import com.example.restfulblogapplication.services.PostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

  private final PostService postService;

  @PostMapping
  public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
    return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
  }

  @GetMapping
  public List<PostDto> getAllPosts() {
    return postService.getAllPosts();
  }

  @GetMapping("/{id}")
  public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id) {
    return ResponseEntity.ok(postService.getPostById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,
                                            @PathVariable(name = "id") Long id) {
    PostDto postResponse = postService.updatePost(postDto, id);
    return new ResponseEntity<>(postResponse, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletePost(@PathVariable(name = "id") Long id) {
    postService.deletePostById(id);
    return new ResponseEntity<>("Post entity deleted successfully!", HttpStatus.NO_CONTENT);
  }
}
