package com.generation.blogpessoal.controller;

import com.generation.blogpessoal.dto.theme.CreateThemeDto;
import com.generation.blogpessoal.dto.theme.ThemeInfoDto;
import com.generation.blogpessoal.dto.theme.UpdateThemeDto;
import com.generation.blogpessoal.service.ThemeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ThemeController {
    @Autowired
    private ThemeService themeService;

    @GetMapping
    public ResponseEntity<List<ThemeInfoDto>> getAll() {
        return ResponseEntity.ok(themeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThemeInfoDto> getById(@PathVariable Long id) {
        ThemeInfoDto theme = themeService.findById(id);
        if (theme == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Theme not found.");

        return ResponseEntity.ok(theme);
    }

    @GetMapping("/descricao/{name}")
    public ResponseEntity<List<ThemeInfoDto>> getAllByName(@PathVariable String name) {
        return ResponseEntity.ok(themeService.findAllByName(name));
    }

    @PostMapping
    public ResponseEntity<ThemeInfoDto> create(@Valid @RequestBody CreateThemeDto theme) {
        return ResponseEntity.status(HttpStatus.CREATED).body(themeService.create(theme));
    }

    @PutMapping
    public ResponseEntity<ThemeInfoDto> update(@Valid @RequestBody UpdateThemeDto theme) {
        return ResponseEntity.ok(themeService.update(theme));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity destroy(@PathVariable Long id) {
        UpdateThemeDto deleteObj = new UpdateThemeDto(id);

        themeService.destroy(deleteObj);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
