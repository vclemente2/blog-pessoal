package com.generation.blogpessoal.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.generation.blogpessoal.domain.dto.post.CreatePostDto;
import com.generation.blogpessoal.domain.dto.post.UpdatePostDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The title field is required.")
    @Size(min = 5, max = 100, message = "The title field must be between 5 and 100 characters long.")
    private String title;

    @NotBlank(message = "The content field is required.")
    @Size(min = 10, max = 1000, message = "The content field must be between 10 and 1000 characters long.")
    private String content;

    @UpdateTimestamp
    private LocalDateTime date;

    @ManyToOne
    @JsonIgnoreProperties("posts")
    private Theme theme;

    @ManyToOne
    @JsonIgnoreProperties("posts")
    private User user;

    public Post(CreatePostDto data) {
        this.title = data.title();

        this.content = data.content();

        Theme theme = new Theme();
        theme.setId(data.themeId());
        this.theme = theme;

        User user = new User();
        user.setId(data.userId());
        this.user = user;
    }

    public Post(UpdatePostDto data) {
        this.id = data.id();

        this.title = data.title();

        this.content = data.content();

        Theme theme = new Theme();
        theme.setId(data.themeId());
        this.theme = theme;

        User user = new User();
        user.setId(data.userId());
        this.user = user;
    }

    public Post() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
