package com.generation.blogpessoal.service;

import com.generation.blogpessoal.dto.theme.CreateThemeDto;
import com.generation.blogpessoal.dto.theme.ThemeInfoDto;
import com.generation.blogpessoal.dto.theme.UpdateThemeDto;
import com.generation.blogpessoal.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeService implements BaseService<CreateThemeDto, UpdateThemeDto, ThemeInfoDto> {
    @Autowired
    private ThemeRepository themeRepository;


    @Override
    public ThemeInfoDto create(CreateThemeDto createThemeDto) {
        return null;
    }

    @Override
    public ThemeInfoDto findById(Long id) {
        return null;
    }

    @Override
    public List<ThemeInfoDto> findAll() {
        return null;
    }

    @Override
    public ThemeInfoDto update(UpdateThemeDto updateThemeDto) {
        return null;
    }

    @Override
    public void destroy(UpdateThemeDto updateThemeDto) {

    }
}
