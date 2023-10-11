package com.generation.blogpessoal.service;

import com.generation.blogpessoal.dto.user.CreateUserDto;
import com.generation.blogpessoal.dto.user.UpdateUserDto;
import com.generation.blogpessoal.dto.user.UserInfoDto;
import com.generation.blogpessoal.model.User;
import com.generation.blogpessoal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserInfoDto create(CreateUserDto data) {
        this.verifyIfEmailIsInUse(data.email());
        User user = new User(data);
        userRepository.save(user);
        return new UserInfoDto(user);
    }

    private void verifyIfEmailIsInUse(String email) {
        boolean isEmailRegistered = userRepository.existsByEmail(email);

        if (isEmailRegistered)
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Email already registered.");
    }

    public List<UserInfoDto> findAll() {
        return userRepository.findAll().stream()
                .map((user) -> new UserInfoDto(user))
                .toList();
    }

    public UserInfoDto findById(Long id) {
        return userRepository.findById(id)
                .map((response) -> new UserInfoDto(response))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));
    }

    public UserInfoDto update(Long id, UpdateUserDto data) {
        this.verifyIfUserExistsById(id);

        Optional<User> user = userRepository.findById(id);

        if (user.get().getEmail() != data.email())
            this.verifyIfEmailIsInUseByOtherUser(data.email(), id);

        user.get().setEmail(data.email());
        user.get().setImage(data.image());
        user.get().setName(data.name());
        user.get().setPassword(data.password());

        userRepository.save(user.get());

        return new UserInfoDto(user.get());
    }

    private void verifyIfUserExistsById(Long id) {
        boolean isExistentId = userRepository.existsById(id);

        if (!isExistentId)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
    }

    private void verifyIfEmailIsInUseByOtherUser(String email, Long id) {
        boolean isEmailRegistered = userRepository.existsByEmailAndIdNot(email, id);

        if (isEmailRegistered)
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Email already registered.");
    }


}
