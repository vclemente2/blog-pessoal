package com.generation.blogpessoal.service;

import com.generation.blogpessoal.dto.theme.CreateThemeDto;
import com.generation.blogpessoal.dto.theme.ThemeInfoDto;
import com.generation.blogpessoal.dto.theme.UpdateThemeDto;
import com.generation.blogpessoal.model.Theme;
import com.generation.blogpessoal.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ThemeService implements BaseService<CreateThemeDto, UpdateThemeDto, ThemeInfoDto> {
    @Autowired
    private ThemeRepository themeRepository;


    @Override
    public ThemeInfoDto create(CreateThemeDto createThemeDto) {
        boolean isExistsTheme = this.isThemeExistsByName(createThemeDto.name());

        if (isExistsTheme)
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "There is already a registered theme with this name.");

        Theme theme = themeRepository.save(new Theme(createThemeDto));

        return new ThemeInfoDto(theme);
    }

    @Override
    public ThemeInfoDto findById(Long id) {
        return themeRepository.findById(id)
                .map((response) -> new ThemeInfoDto(response))
                .orElse(null);
    }

    @Override
    public List<ThemeInfoDto> findAll() {
        return themeRepository.findAll().stream()
                .map((response) -> new ThemeInfoDto(response))
                .toList();
    }

    @Override
    public ThemeInfoDto update(UpdateThemeDto updateThemeDto) {
        if (!isThemeExistsById(updateThemeDto.id()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Theme not found.");

        if (isThemeExistsByNameToAnotherId(updateThemeDto.name(), updateThemeDto.id()))
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Theme name already registered.");

        Theme updatedTheme = themeRepository.save(new Theme(updateThemeDto));

        return new ThemeInfoDto(updatedTheme);
    }

    @Override
    public void destroy(UpdateThemeDto updateThemeDto) {
        if (!isThemeExistsById(updateThemeDto.id()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Theme not found.");

        themeRepository.deleteById(updateThemeDto.id());
    }

    private boolean isThemeExistsById(Long id) {
        return themeRepository.existsById(id);
    }

    public boolean isThemeExistsByNameToAnotherId(String name, Long id) {
        return themeRepository.existsByNameAndIdNot(name, id);
    }

    private boolean isThemeExistsByName(String name) {
        return themeRepository.existsByName(name);
    }

    public List<ThemeInfoDto> findAllByName(String name) {
        return themeRepository.findAllByNameContainingIgnoreCase(name).stream()
                .map((response) -> new ThemeInfoDto(response))
                .toList();
    }
}
