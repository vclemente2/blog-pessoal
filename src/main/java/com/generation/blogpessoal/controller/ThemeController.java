package com.generation.blogpessoal.controller;

import com.generation.blogpessoal.model.Theme;
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
@RequestMapping("/temas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ThemeController {
    @Autowired
    private ThemeRepository themeRepository;

    @GetMapping
    public ResponseEntity<List<Theme>> getAll() { return ResponseEntity.ok(themeRepository.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<Theme> getById(@PathVariable Long id) {
        return themeRepository.findById(id)
                .map(response->ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/descricao/{name}")
    public ResponseEntity<List<Theme>> getAllByName(@PathVariable String name){
        return ResponseEntity.ok(themeRepository.findAllByNameContainingIgnoreCase(name));
    }

    @PostMapping
    public ResponseEntity<Theme> create(@Valid @RequestBody Theme theme){
        Optional<Theme> alreadyExistingTheme = themeRepository.findByNameIgnoreCase(theme.getName());

        if(alreadyExistingTheme.isPresent())
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "There is already a registered theme with this name.");

        return ResponseEntity.status(HttpStatus.CREATED).body(themeRepository.save(theme));
    }

    @PutMapping
    public ResponseEntity<Theme> update(@Valid @RequestBody Theme theme){
        if(theme.getId() == null)
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Id field is required." );

     return themeRepository.findById(theme.getId())
                .map((response)->ResponseEntity.ok(themeRepository.save(theme)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity destroy(@PathVariable Long id){
       Optional<Theme> theme = themeRepository.findById(id);

       if(theme.isEmpty())
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Theme not found.");

       themeRepository.delete(theme.get());
       return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
