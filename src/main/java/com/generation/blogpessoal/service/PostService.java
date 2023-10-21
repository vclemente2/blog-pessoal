package com.generation.blogpessoal.service;

import com.generation.blogpessoal.domain.dto.post.CreatePostDto;
import com.generation.blogpessoal.domain.dto.post.PostInfoDto;
import com.generation.blogpessoal.domain.dto.post.UpdatePostDto;
import com.generation.blogpessoal.domain.model.Post;
import com.generation.blogpessoal.domain.model.Theme;
import com.generation.blogpessoal.domain.model.User;
import com.generation.blogpessoal.domain.repository.PostRepository;
import com.generation.blogpessoal.domain.repository.ThemeRepository;
import com.generation.blogpessoal.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PostService implements BaseService<CreatePostDto, UpdatePostDto, PostInfoDto> {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ThemeRepository themeRepository;

    @Override
    public PostInfoDto create(CreatePostDto createPostDto) {
        verifyIfUserAndThemeExists(createPostDto.userId(), createPostDto.themeId());

        Post post = postRepository.save(new Post(createPostDto));

        return new PostInfoDto(post);
    }

    @Override
    public PostInfoDto findById(Long id) {
        return postRepository.findById(id)
                .map((response) -> new PostInfoDto(response))
                .orElse(null);
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found."));
    }

    @Override
    public List<PostInfoDto> findAll() {
        return postRepository.findAll().stream()
                .map(PostInfoDto::new)
                .toList();
    }

    @Override
    public PostInfoDto update(UpdatePostDto updatePostDto) {
        verifyIfUserAndThemeExists(updatePostDto.userId(), updatePostDto.themeId());

        Optional<Post> post = postRepository.findById(updatePostDto.id());

        if (post.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found.");

        post.get().setId(updatePostDto.id());
        post.get().setContent(updatePostDto.content());
        post.get().setTitle(updatePostDto.title());

        Theme theme = new Theme();
        theme.setId(updatePostDto.themeId());
        post.get().setTheme(theme);

        User user = new User();
        user.setId(updatePostDto.userId());
        post.get().setUser(user);

        postRepository.save(post.get());

        return new PostInfoDto(post.get());
    }

    @Override
    public void destroy(UpdatePostDto updatePostDto) {
        if (!postRepository.existsById(updatePostDto.id()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found.");

        postRepository.deleteById(updatePostDto.id());
    }

    private void verifyIfUserAndThemeExists(Long userId, Long themeId) {
        if (!userRepository.existsById(userId))
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "There is no user with this id.");
        if (!themeRepository.existsById(themeId))
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "There is no theme with this id.");
    }

    public List<PostInfoDto> findAll(String title, String content) {
        title = title == null ? "" : title;
        content = content == null ? "" : content;

        return postRepository.findAllByTitleContainingAndContentContainingAllIgnoreCase(title, content)
                .stream()
                .map(PostInfoDto::new)
                .toList();
    }
}
