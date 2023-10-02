package com.generation.blogpessoal.controller;

import com.generation.blogpessoal.model.Post;
import com.generation.blogpessoal.repository.PostRepository;
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

//    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @PostMapping
    public ResponseEntity<Post> create(@Valid @RequestBody Post post){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postRepository.save(post));
    }

    @PutMapping
    public ResponseEntity<Post> update(@Valid @RequestBody Post post){
        if (post.getId() == null)
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "O Campo 'id' é obrigatório.");

        return postRepository.findById(post.getId())
                .map(response -> ResponseEntity.status(HttpStatus.OK)
                        .body(postRepository.save(post)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void destroy(@PathVariable Long id){
        Optional<Post> post = postRepository.findById(id);

        if(post.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        postRepository.deleteById(id);
    }
}
