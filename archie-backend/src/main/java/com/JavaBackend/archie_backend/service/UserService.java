package com.JavaBackend.archie_backend.service;

import com.JavaBackend.archie_backend.dto.SignupRequest;
import com.JavaBackend.archie_backend.model.User;
import com.JavaBackend.archie_backend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User signup(SignupRequest request) {

        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        // Hash password
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        // Create user
        User user = new User(
                request.getEmail(),
                request.getUsername(),
                hashedPassword
        );

        return userRepository.save(user);
    }
}
