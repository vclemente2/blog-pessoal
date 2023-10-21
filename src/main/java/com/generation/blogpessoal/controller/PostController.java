package com.generation.blogpessoal.controller;

import com.generation.blogpessoal.domain.dto.post.CreatePostDto;
import com.generation.blogpessoal.domain.dto.post.PostInfoDto;
import com.generation.blogpessoal.domain.dto.post.UpdatePostDto;
import com.generation.blogpessoal.domain.model.Post;
import com.generation.blogpessoal.domain.repository.PostRepository;
import com.generation.blogpessoal.domain.repository.ThemeRepository;
import com.generation.blogpessoal.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<PostInfoDto>> getAll(@RequestParam(name = "titulo", required = false) String title, @RequestParam(name = "descricao", required = false) String content) {
        return ResponseEntity.ok(postService.findAll(title, content));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostInfoDto> getByID(@PathVariable Long id) {
        Optional<PostInfoDto> post = Optional.ofNullable(postService.findById(id));

        return post
                .map(response -> ResponseEntity.ok(post.get()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found."));
    }

    @GetMapping("/titulo/{title}")
    public ResponseEntity<List<PostInfoDto>> getAllByTitle(@PathVariable String title) {
        return ResponseEntity.ok(postService.findAll(title, null));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<PostInfoDto> create(@Valid @RequestBody CreatePostDto post) {
        PostInfoDto createdPost = postService.create(post);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdPost);
    }

    @Transactional
    @PutMapping
    public ResponseEntity<PostInfoDto> update(@Valid @RequestBody UpdatePostDto post) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.update(post));
    }

    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void destroy(@PathVariable Long id) {
        UpdatePostDto deleteData = new UpdatePostDto(id);

        postService.destroy(deleteData);
    }
}
