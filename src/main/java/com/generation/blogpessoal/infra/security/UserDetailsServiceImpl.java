package com.generation.blogpessoal.infra.security;


import com.generation.blogpessoal.domain.dto.security.LoginDataDto;
import com.generation.blogpessoal.domain.model.User;
import com.generation.blogpessoal.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        return new UserDetailsImpl(new LoginDataDto(user.get()));
    }
}
