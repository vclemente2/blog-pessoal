package com.generation.blogpessoal.service;

import com.generation.blogpessoal.domain.dto.security.LoginDataDto;
import com.generation.blogpessoal.domain.dto.security.TokenDataDto;
import com.generation.blogpessoal.domain.dto.user.CompleteUpdateUserDto;
import com.generation.blogpessoal.domain.dto.user.CreateUserDto;
import com.generation.blogpessoal.domain.dto.user.UserInfoDto;
import com.generation.blogpessoal.domain.model.User;
import com.generation.blogpessoal.domain.repository.UserRepository;
import com.generation.blogpessoal.infra.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements BaseService<CreateUserDto, CompleteUpdateUserDto, UserInfoDto> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserInfoDto create(CreateUserDto data) {
        if (isEmailAlreadyRegistered(data.email()))
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Email already registered.");

        User user = new User(data);
        user.setPassword(this.encryptPassword(user.getPassword()));

        userRepository.save(user);
        return new UserInfoDto(user);
    }

    private boolean isEmailAlreadyRegistered(String email) {
        return userRepository.existsByEmail(email);
    }

    private String encryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    @Override
    public UserInfoDto findById(Long id) {
        return userRepository.findById(id)
                .map((response) -> new UserInfoDto(response))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));
    }

    @Override
    public List<UserInfoDto> findAll() {
        return userRepository.findAll().stream()
                .map((user) -> new UserInfoDto(user))
                .toList();
    }

    @Override
    public UserInfoDto update(CompleteUpdateUserDto data) {
        String email = jwtService.extractUserEmail(jwtService.extractOnlyHashPartOfToken(data.token()));

        if (!this.isUserExistsByEmail(email) || !this.isUserExistsById(data.id()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");


        Optional<User> user = userRepository.findByEmail(email);

        if (user.get().getId() != data.id()) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        if (user.get().getEmail() != data.email())
            if (this.isEmailInUseByOtherUser(data.email(), data.id()))
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Email already registered.");

        user.get().setEmail(data.email());
        user.get().setImage(data.image());
        user.get().setName(data.name());
        user.get().setPassword(this.encryptPassword(data.password()));

        userRepository.save(user.get());

        return new UserInfoDto(user.get());
    }


    private boolean isUserExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    private boolean isUserExistsById(Long id) {
        return userRepository.existsById(id);
    }

    private boolean isEmailInUseByOtherUser(String email, Long id) {
        return userRepository.existsByEmailAndIdNot(email, id);
    }

    @Override
    public void destroy(CompleteUpdateUserDto data) {
        String userEmail = jwtService.extractUserEmail(jwtService.extractOnlyHashPartOfToken(data.token()));

        if (!this.isUserExistsByEmail(userEmail) || !this.isUserExistsById(data.id()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");

        Optional<User> user = userRepository.findByEmail(userEmail);

        if (user.get().getId() != data.id())
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        userRepository.delete(user.get());
    }

    public TokenDataDto authenticateUser(LoginDataDto loginData) {
        var credentials = new UsernamePasswordAuthenticationToken(loginData.email(), loginData.password());

        Authentication authentication = authenticationManager.authenticate(credentials);

        if (authentication.isAuthenticated()) {
            if (!this.isUserExistsByEmail(loginData.email()))
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Incorrect email or password.");

            String token = this.generateToken(loginData.email());

            return new TokenDataDto(token);
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Incorrect email or password.");
    }

    private String generateToken(String email) {
        return jwtService.generateToken(email);
    }
}
