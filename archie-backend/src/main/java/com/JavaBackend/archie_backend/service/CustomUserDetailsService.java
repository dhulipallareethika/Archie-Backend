package com.JavaBackend.archie_backend.service;

import com.JavaBackend.archie_backend.model.User;
import com.JavaBackend.archie_backend.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
    // Search by MongoDB _id (userId) instead of email
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + userId));

    return new org.springframework.security.core.userdetails.User(
            user.getUserId(), // Use the ID as the principal username
            user.getPassword(),
            Collections.emptyList()
    );
}

}
