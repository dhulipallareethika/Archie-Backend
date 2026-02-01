package com.JavaBackend.archie_backend.service;

import com.JavaBackend.archie_backend.config.JwtUtil;
import com.JavaBackend.archie_backend.dto.LoginRequest;
import com.JavaBackend.archie_backend.dto.SignupRequest;
import com.JavaBackend.archie_backend.exception.EmailAlreadyExistsException;
import com.JavaBackend.archie_backend.exception.InvalidCredentialsException;
import com.JavaBackend.archie_backend.model.User;
import com.JavaBackend.archie_backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String registerUser(SignupRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        userRepository.save(user);

        return "User registered successfully";
    }

    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        return jwtUtil.generateToken(
                user.getUserId(),
                user.getEmail()
        );
    }
}
