package com.generation.blogpessoal.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.generation.blogpessoal.domain.dto.theme.CreateThemeDto;
import com.generation.blogpessoal.domain.dto.theme.UpdateThemeDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "tb_themes")
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The name field is required.")
    @Column(unique = true)
    @Size(max = 255, message = "The name must not be longer than 255 characters.")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "theme", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("theme")
    private List<Post> posts;

    public Theme(CreateThemeDto data) {
        this.name = data.name();
    }

    public Theme(UpdateThemeDto data) {
        this.id = data.id();
        this.name = data.name();
    }

    public Theme() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
