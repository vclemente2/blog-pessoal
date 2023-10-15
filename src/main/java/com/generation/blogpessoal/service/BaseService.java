package com.generation.blogpessoal.service;


import java.util.List;

public interface BaseService<CreateDto, UpdateDto, InfoDto> {
    InfoDto create(CreateDto createDto);

    InfoDto findById(Long id);

    List<InfoDto> findAll();

    InfoDto update(UpdateDto dto);

    void destroy(UpdateDto dto);
}
