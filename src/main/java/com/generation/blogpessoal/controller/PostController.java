package com.generation.blogpessoal.controller;

import com.generation.blogpessoal.model.Post;
import com.generation.blogpessoal.model.Theme;
import com.generation.blogpessoal.repository.PostRepository;
import com.generation.blogpessoal.repository.ThemeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ThemeRepository themeRepository;

    @GetMapping
    public ResponseEntity<List<Post>> getAll(){
        return ResponseEntity.ok(postRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getByID(@PathVariable Long id){
        return postRepository.findById(id)
                .map((response) -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/titulo/{title}")
    public ResponseEntity<List<Post>> getAllByTitle(@PathVariable String title){
        return ResponseEntity.ok(postRepository.findAllByTitleContainingIgnoreCase(title));
    }

    @PostMapping
    public ResponseEntity<Post> create(@Valid @RequestBody Post post){
        if(post.getTheme() == null)
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Theme field is required.");

        Optional<Theme> theme = themeRepository.findById(post.getTheme().getId());

        if(theme.isEmpty())
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "There is no theme registered with this id.");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postRepository.save(post));
    }

    @PutMapping
    public ResponseEntity<Post> update(@Valid @RequestBody Post post){
        if (post.getId() == null)
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Id field is required.");

        if (postRepository.existsById(post.getId())){
            if (post.getTheme() == null)
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Theme field is required.");

            if (themeRepository.existsById(post.getTheme().getId())){
                return ResponseEntity.status(HttpStatus.OK)
                        .body(postRepository.save(post));
            }
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "There is no theme registered with this id.");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void destroy(@PathVariable Long id){
        Optional<Post> post = postRepository.findById(id);

        if(post.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found.");

        postRepository.deleteById(id);
    }
}
